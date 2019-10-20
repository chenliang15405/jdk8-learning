package com.jdk8.features;

import com.jdk8.features.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * 流的中间操作：
 *
 * 筛选与切片
 *  filter -> 接收lambda，从流中排除某些元素
 *  limit -> 截断流，使其元素不超过给定数量
 *  skip(n) -> 跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流 与limit互补
 *  distinct -> 筛选，通过流所生成元素的hashcode()和equals()去除重复元素
 *
 *
 * 映射
 *  map -> 接收lambda，将元素转换为其他形式或者提取信息，接收一个函数为参数，该函数会应用到每个元素上，并将其映射成一个元素
 *  flatMap -> 接收一个函数作为参数，将流中的每个值都替换为另一个流，然后把所有的流连接成一个流
 *
 *
 * 排序
 *  sorted() -> 自然排序
 *  sorted(Comparator comparator) -> 定制排序
 *
 */
public class StreamApiTest2 {

    List<Employee> list = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 28, 112.33),
            new Employee(103, "王五", 18, 93099.51),
            new Employee(104, "赵六", 38, 313.01),
            new Employee(105, "田七", 58, 3392.00),
            new Employee(105, "田七", 58, 3392.00)
    );


    @Test
    public void test1() {
        Stream<Employee> stream = list.stream().filter((e) -> {
            System.out.println("stream api 的中间操作在没有终止操作，则不执行");
            return e.getAge() > 35;
        });

        // 终止操作，一次性执行全部stream的操作
        stream.forEach(System.out::println);
    }


    @Test
    public void test2() {
        list.stream()
                .filter(e -> e.getId() > 101)
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        list.stream()
                .filter(e -> e.getId() > 100)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        list.stream()
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test5() {
        List<String> strList = Arrays.asList("aa", "bb", "cc", "dd", "ee");
        strList.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);

        // map和js中的map一样，循环一遍数据，返回一个新的stream
        list.stream()
                .map(Employee::getName)
                .forEach(System.out::println);


        // 将函数作为map的参数
        //Stream<Stream<Character>> streamList = strList.stream()
        //        .map(StreamApiTest2::filterCharater);
        //
        //streamList.forEach(sm -> {
        //    sm.forEach(System.out::println);
        //});

        Stream<Character> flatStream = strList.stream()
                .flatMap(StreamApiTest2::filterCharater);

        flatStream.forEach(System.out::println);

    }

    public static Stream<Character> filterCharater(String str) {
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }


    @Test
    public void test6() {
       List<String> list1 = Arrays.asList("aa", "bb", "ccc");
       list1.stream()
               .sorted() // 自然排序自能排序基本类型，不能排序引用类型，引用类型的排序需要使用定制排序，自定义排序规则
               .forEach(System.out::println);


       list.stream()
                .sorted((x1, x2) -> {
                    if(x1.getAge().equals(x2.getAge())) {
                        return x1.getName().compareTo(x2.getName());
                    } else {
                        return -Integer.compare(x1.getAge(), x2.getAge());
                    }
                })
               .forEach(System.out::println);

    }




}
