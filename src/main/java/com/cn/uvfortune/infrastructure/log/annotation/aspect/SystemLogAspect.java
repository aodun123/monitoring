package com.cn.uvfortune.infrastructure.log.annotation.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cn.uvfortune.infrastructure.log.annotation.LogService;
import com.cn.uvfortune.infrastructure.log.annotation.LogModel;
import com.cn.uvfortune.infrastructure.log.annotation.Log;
import com.cn.uvfortune.infrastructure.log.annotation.util.ExplUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;


/**
 * @author WangXiao
 * Email: 397052677@qq.com
 * @version 创建时间：2018-07-23
 */

@Aspect
@Component
public class SystemLogAspect {

    @Autowired
    private LogService logService;

    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);


    /**
     * 方法后置通知 —————— 将日志信息插入到数据库
     *
     * @param joinPoint
     * @param log
     */
    @AfterReturning("@annotation(log)")
    public void afterReturn(JoinPoint joinPoint, Log log) {
        //用户id
        Integer user_id = getUserId();
        //客户端ip
        String ip = getIp();
        //日志类型
        String type = log.type();
        //日志等级
        String level = log.level();
        //日志描述
        String content = log.content();
        //解析日志描述里的el表达式
        content = analysisAndReplace(content, joinPoint);

        LogModel logModel = new LogModel();
        logModel.setOperationUserId(user_id);
        logModel.setIpAddress(ip);
        logModel.setLogLevel(level);
        logModel.setLogType(type);
        logModel.setLogContent(content);
        logService.saveLog(logModel);
        logger.info("日志记录:" + logModel);
    }

    /**
     * 异常通知 用于拦截记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "@annotation(log)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log log, Throwable e) {
        logger.error(log.content(), e);
    }

    /**
     * 将aop切点所指向的方法参数名、参数值 放入context容器
     *
     * @param standardEvaluationContext context
     * @param joinPoint
     * @return
     */
    private StandardEvaluationContext setContextVariables(StandardEvaluationContext standardEvaluationContext, JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Method targetMethod = methodSignature.getMethod();

        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

        String[] parametersName = discoverer.getParameterNames(targetMethod);

        if (args == null || args.length <= 0) {

            return standardEvaluationContext;

        }

        for (int i = 0; i < args.length; i++) {

            standardEvaluationContext.setVariable(parametersName[i], args[i]);

        }
        return standardEvaluationContext;
    }


    /**
     * 获取客户端ip地址
     */
    private String getIp() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取当前session的用户id
     */
    private Integer getUserId() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (session.getAttribute("user_id") != null) {
            return Integer.valueOf(session.getAttribute("user_id").toString());
        }
        return null;
    }


    @Autowired
    ExplUtils explUtils;

    /**
     * 解析字符串中所有的EL表达式，并将内容替换后返回
     *
     * @param str       需解析处理的字符串
     * @param joinPoint
     * @return
     */
    private String analysisAndReplace(String str, JoinPoint joinPoint) {

        if (str == null || str.length() == 0) {
            return str;
        }

        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(joinPoint.getArgs());

        standardEvaluationContext = setContextVariables(standardEvaluationContext, joinPoint);

        StringBuffer bufferStr = new StringBuffer(str);
        int start = 0, end = 0;
        for (int i = 0; i < bufferStr.length(); i++) {
            char c = bufferStr.charAt(i);
            if (c == '{') {
                start = i;
                end = bufferStr.indexOf("}", start);
            }
            if (end > start) {
                String el = bufferStr.substring(start + 1, end).trim();
                String val = explUtils.generateKey(el, standardEvaluationContext);
                bufferStr = bufferStr.replace(start, end + 1, val);
                end = 0;
            }
        }
        return bufferStr.toString();
    }
}