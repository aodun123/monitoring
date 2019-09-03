package com.cn.uvfortune.infrastructure.log.annotation;

import java.lang.annotation.*;


/**
 * @author WangXiao
 * @create 2018/7/23
 * 记录操作日志、系统日志注解
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 日志类型
     */
    String type() default "1";

    /**
     * 日志等级
     */
    String level() default "1";

    /**
     * 日志内容
     */
    String content();

}
