package com.pyy.springsecuritydemo.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author panyangyi
 * @create 2020/3/24 11:03
 *
 * @Target：注解的作用目标，依次从 type、field、parameter、method、constructor。
 *
 * @Target(ElementType.TYPE)：作用于接口、类、枚举、注解
 * @Target(ElementType.FIELD)：作用于字段、枚举的常量
 * @Target(ElementType.METHOD)：作用于方法
 * @Target(ElementType.PARAMETER)：作用于方法参数
 * @Target(ElementType.CONSTRUCTOR)：作用于构造函数
 * @Retention：注解的保留位置
 *
 * RetentionPolicy.SOURCE：源代码级别保留，编译时忽略，在 class 文件中不存在。
 *
 * RetentionPolicy.CLASS：编译时被保留，默认的保留策略，在 class 文件中存在，运行时会忽略。
 *
 * RetentionPolicy.RUNTIME：运行时将被 JVM 保留，在运行时可以被 JVM 或其他使用反射机制的代码所读取和使用。
 *
 * @Document：说明该注解将被包含在 javadoc 中。
 *
 * @Inherited：说明子类可以继承父类中的该注解。
 *
 *
 *
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreToken {
    boolean required() default true;

}
