package com.jdk8.features;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用
 *
 *  方法引用就是特殊的Lambda，可以看作是在符合一定的参数列表和返回值类型的条件下，简化格式的Lambda
 *
 * 1. 类::静态方法 或者 实例::实例方法
 *
 *    当该方法的参数列表（类型、个数）以及返回类型等都和Lambda表达式的参数列表和返回类型一致时可以直接使用方法引用来替换lambda
 *
 * 2. 类::实例方法
 *
 *    当参数列表和lambda表达式的参数列表不同的时候可以使用这种方法引用来替换
 *
 * @author alan.chen
 * @date 2020/8/1 3:36 PM
 */
public class MethodRefTest {


    /**
     * 类::实例方法
     *
     * Comparator中的 int compare(T t1, T t2)
     * String中的int t1.compareTo(t2)
     *
     * 类::实例方法的时候，会有一个this指向
     */
    @Test
    public void test1() {
        Comparator<String> com = (a, b) -> a.compareTo(b);
        System.out.println(com.compare("abc", "bac"));

        Comparator<String> com1 = String::compareTo;
        System.out.println(com1.compare("abc", "bac"));
    }

    /**
     * 类::静态方法
     *
     * Function 中的 R apply(T t)
     * Math中的Long round(Double d)
     *
     */
    @Test
    public void test2() {
        Function<Double, Long> function = d -> Math.round(d);
        System.out.println(function.apply(2.3));

        Function<Double, Long> function1 = Math::round;
        System.out.println(function1.apply(2.5));
    }

    /**
     * 实例::实例方法
     */
    @Test
    public void test3() {
        Person p = new Person("1", 123);

        Supplier<String> supplier = () -> p.getName();
        System.out.println(supplier.get());

        Supplier<String> supplier1 = p::getName;
        System.out.println(supplier1.get());

    }

    class X {
        String f() { return "X::f()"; }
    }

    interface MakeString {
        String make();
    }

    interface TransformX {
        String transform(X x);
    }

    @Test
    public void test5() {
        // MakeString ms = X::f; // [1]，类::实例方法 会产生一个this实例，如果方法列表相同的情况下，需要类::静态方法
        TransformX sp = X::f; // 其实就是将x的实例引用传递给该方法，通过实例引用调用f()方法
        X x = new X();
        System.out.println(sp.transform(x)); // [2]
        System.out.println(x.f()); // 同等效果
    }


    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
