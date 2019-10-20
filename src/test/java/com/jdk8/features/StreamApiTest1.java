package com.jdk8.features;

import com.jdk8.features.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤
 *
 *  1. 创建stream
 *
 *  2.  中间操作
 *
 *  3. 终止操作
 *
 *  stream操作是延迟操作，在没有终止操作的时候，任何的中间操作是不执行的
 *
 *
 *
 */
public class StreamApiTest1 {


    @Test
    public void test1() {
        // 1. 可以通过Collection系列集合提供的stream()或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //2. 通过Array中的静态方法stream()
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        //3. 通过stream类的静态方法of()
        Stream<String> aa = Stream.of("aa", "bb", "cc");

        //4. 创建无限流
        // 迭代
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
        iterate.limit(10).forEach(System.out::println);

        // 生成
        Stream.generate(() -> Math.random()).forEach(System.out::println);
    }


}
