package com.cn.uvfortune.infrastructure.log.annotation.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * EL表达式注入
 */

@Component
public class ExplUtils implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ExplUtils.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String generateKey(String key, StandardEvaluationContext context) {

        context.setBeanResolver(new BeanFactoryResolver(this.applicationContext));

        ExpressionParser parser = new SpelExpressionParser();

        Expression exp = parser.parseExpression(key);

        Object value = exp.getValue(context, Object.class);

        Class clazz = value.getClass();

        if (clazz.isPrimitive() || clazz == String.class || clazz == Date.class) {
            return value.toString();
        } else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
            }
            return value.toString();
        }
    }


}