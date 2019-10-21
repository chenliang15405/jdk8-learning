package com.jdk8.features;

import com.jdk8.features.annomation.MyAnnomation;
import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解和类型注解
 *
 *  重复注解： 可以在同一个地方上定义两个相同的注解
 *
 *  类型注解： 定义一个注解，指定该变量的类型
 *
 */
public class AnnomationTest {

    // 类型注解类似于
    private @NotNull Object obj;


    @Test
    public void test1() throws NoSuchMethodException {
        Class<AnnomationTest> clazz = AnnomationTest.class;

        Method method = clazz.getMethod("show");
        MyAnnomation[] myAnnomations = method.getAnnotationsByType(MyAnnomation.class);

        for (MyAnnomation annomation : myAnnomations) {
            System.out.println(annomation.value());
        }

    }

    @MyAnnomation("hello")
    @MyAnnomation("world")
    public void  show(@MyAnnomation("123") String str) {

    }

}
