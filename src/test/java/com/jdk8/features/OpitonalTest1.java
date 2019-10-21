package com.jdk8.features;

import com.jdk8.features.pojo.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional类常用方法：
 *
 *  Optional.of(T t) 创建一个optional实例
 *  Optional.empty() 创建一个空的optional
 *  Optional.ofNullable(T t) 如果t不为null，创建optional实例，否则创建空实例
 *  isPresent() 判断是否包含值
 *  orElse(T t) 如果调用对象包含值，返回该值，否则返回t
 *  orElseGet(Supplier s) 如果调用对象包含值，返回该值, 否则返回s获取的值
 *  map(Function f) 如果有值对其处理，并返回处理后的Optional, 否则返回Optional.empty()
 *  flatMap(Function mapper) 与map类似，要求返回必须是Optional
 *
 *
 */
public class OpitonalTest1 {

    @Test
    public void test1() {
        Optional<Employee> op = Optional.ofNullable(null);

        Employee em = op.orElseGet(() -> new Employee(123));
        System.out.println(em);

    }

    @Test
    public void test2() {
        Optional<Employee> op = Optional.ofNullable(new Employee(123, "111",18, 12.3));

        Optional<String> optional = op.map(Employee::getName);
        System.out.println(optional.get());

    }

    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(new Employee(123, "111",18, 12.3));

        Optional<String> optional = op.flatMap(e -> Optional.of(e.getName()));
        System.out.println(optional.get());

    }
}
