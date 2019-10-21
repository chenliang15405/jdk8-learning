package com.jdk8.features.annomation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

@Repeatable(MyAnnomations.class) // 指定多个注解的数组的类型
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER}) // TYPE_PARAMETER定义类型注解
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnomation {

    String value() default "java annomation";

}
