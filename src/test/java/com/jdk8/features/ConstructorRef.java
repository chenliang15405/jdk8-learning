package com.jdk8.features;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * 构造器引用
 *   参数列表相同则可以使用构造器引用，返回值即为创建的对象
 *
 *   如果需要更多的构造函数，可以自定义函数式接口
 *
 * 数组引用
 *   将数组看作为一个类，写法和构造器相同
 *
 * @author alan.chen
 * @date 2020/8/1 3:50 PM
 */
public class ConstructorRef {


    /**
     * 构造器引用
     *
     * 无参构造器
     */
    @Test
    public void test1() {
        Supplier<Person> p = () -> new Person();
        System.out.println(p.get());

        /**
         * 决定使用的是什么构造函数，是通过函数式接口的方法决定，Supplier接口不接收参数，返回指定对象，所以调用的是无参构造器
         */
        Supplier<Person> p1 = Person :: new;
        System.out.println(p1.get());

    }

    /**
     * 构造器引用
     *
     * 有参数构造器
     *
     * BiFunction
     *
     */
    @Test
    public void test2() {
        // 前面2个是参数类型，最后一个泛型为返回值类型
        // 如果需要更多的参数类型也可以自定义函数式接口
        BiFunction<String, Integer, Person> biFunction = (name, age) -> new Person(name, age);
        System.out.println(biFunction.apply("123", 12));

        /**
         * BiFunction 接收2个参数并返回一个对象，所以可以接收2个参数的构造器
         */
        BiFunction<String, Integer, Person> biFunction1 = Person::new;
        System.out.println(biFunction1.apply("321", 21));
    }

    /**
     * 数组引用
     *
     * Function  R apply(T t)
     *
     */
    @Test
    public void test3() {
        // 使用lambda表示
        Function<Integer, String[]> function = length -> new String[length];
        System.out.println(function.apply(10));

        // 直接使用方法引用表示
        Function<Integer, String[]> function1 = String[]::new;
        System.out.println(function.apply(10));

    }



    class Person {
        private String name;
        private int age;

        public Person() {
        }

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

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
