package com.jdk8.features;

import com.jdk8.features.pojo.Peple;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 *
 *  查找与匹配
 *      allMatch -》 检查是否匹配所有元素，所有元素都满足要求
 *      anyMatch -> 检查是否至少匹配一个元素
 *      noneMatch -> 检查是否没有匹配所有元素
 *      findFirst -> 返回第一个元素
 *      findAny -> 返回当前流中的任意元素
 *      count -> 返回流中元素的总个数
 *      max -> 返回流中最大值
 *      min -> 返回流中最小值
 *
 *
 *  归均
 *      reduce(T identity, BinaryOperator) / reduce(BinaryOperator) -> 将流中元素反复结合起来，得到一个值
 *
 *      BinaryOperator<T> 表示接收 T t1, T t2，返回类型T ，所以传递的函数式接口或者定义lambda需要符合该参数列表和返回值类型
 *
 *  收集
 *
 *    collect -> 将流转换为其他形式，接收一个Collector接口的实现，用于将元素汇总的方法
 *
 */
public class StreamApiTest3 {


    List<Peple> peples = Arrays.asList(
            new Peple(101, "张三", 18, 9999.99, "busy"),
            new Peple(102, "李四", 28, 112.33, "free"),
            new Peple(103, "王五", 18, 93099.51, "vocation"),
            new Peple(104, "赵六", 38, 313.01, "free"),
            new Peple(105, "田七", 58, 3392.00, "busy"),
            new Peple(105, "田七", 58, 3392.00, "free")
    );




    @Test
    public void test1() {
        boolean b1 = peples.stream()
                .allMatch(e -> e.getStatus().equals("busy"));
        System.out.println(b1);

        boolean b2 = peples.stream()
                .anyMatch(e -> "busy".equals(e.getStatus()));
        System.out.println(b2);

        boolean b3 = peples.stream()
                .noneMatch(e -> "busy".equals(e.getStatus()));
        System.out.println(b3);

        Optional<Peple> optional = peples.stream()
                                            .findFirst();
        Peple peple = optional.orElse(new Peple());
        System.out.println(peple);


        // 串型流，找到就返回
        Optional<Peple> free = peples.stream()
                                        .filter(e -> e.getStatus().equals("free"))
                                        .findAny();
        System.out.println(free.get());

        // 并行流，多个线程一起找，谁先找到就直接返回
        Optional<Peple> free2 = peples.parallelStream()
                .filter(e -> e.getStatus().equals("free"))
                .findAny();
        System.out.println(free2.get());

    }

    @Test
    public void test2() {
        long count = peples.stream()
                .count();
        System.out.println(count);

        Optional<Peple> max = peples.stream()
                .max((e1, e2) -> e1.getAge().compareTo(e2.getAge()));
        System.out.println(max.get());


        Optional<Integer> min = peples.stream()
                .map(Peple::getAge)
                .min(Integer::compare);
        System.out.println(min.get());

    }


    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

        Integer reduce = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        Optional<Integer> op = peples.stream()
                .map(Peple::getId)
                .reduce(Integer::sum);
        System.out.println(op.get());
    }


    @Test
    public void test4() {
        List<String> collect = peples.stream()
                .map(Peple::getName)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test5() {
        Set<String> collect = peples.stream()
                .map(Peple::getName)
                .collect(Collectors.toSet());
        System.out.println(collect);
    }

    @Test
    public void test6() {
        Set<String> collect = peples.stream()
                .map(Peple::getName)
                .collect(Collectors.toCollection(HashSet::new)); // 自定义收集的容器
        System.out.println(collect);
    }

    @Test
    public void test7() {
        Long collect = peples.stream()
                .collect(Collectors.counting());

        Double d = peples.stream()
                .collect(Collectors.averagingInt(Peple::getId)); // 对流中的指定的整数元素求平均值

        Optional<Peple> op = peples.stream()
                            .collect(Collectors.maxBy((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())));

    }

    // 分组
    @Test
    public void test8() {
        Map<String, List<Peple>> map = peples.stream()
                .collect(Collectors.groupingBy(Peple::getStatus));
        System.out.println(map);

    }

    // 多级分组
    @Test
    public void test9() {
        Map<Integer, Map<String, List<Peple>>> map = peples.stream()
                // 可以多级分组，先按照age分组，然后再按照id分组
                .collect(Collectors.groupingBy(Peple::getAge, Collectors.groupingBy(e -> {
                    if (e.getId() > 102) {
                        return "大id";
                    } else {
                        return "小id";
                    }
                })));
        System.out.println(map);
    }


    // 分区
    @Test
    public void test10() {
        Map<Boolean, List<Peple>> map = peples.stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() > 30));
        // 满足条件的在一组，没有满足的在一组
        System.out.println(map);
    }

    // 统一获取数值
    @Test
    public void test11() {
        IntSummaryStatistics collect = peples.stream()
                .collect(Collectors.summarizingInt(Peple::getAge)); // 对流中的元素收集为统计值

        System.out.println(collect.getAverage());
        System.out.println(collect.getCount());
        System.out.println(collect.getSum());
    }


    // 拼接字符串
    @Test
    public void test12() {
        String str1 = peples.stream()
                .map(Peple::getName)
                .collect(Collectors.joining());
        System.out.println(str1);

        String str2 = peples.stream()
                .map(Peple::getName)
                .collect(Collectors.joining(",")); // 逗号分隔
        System.out.println(str2);

        String str3 = peples.stream()
                .map(Peple::getName)
                .collect(Collectors.joining(",","===","+++")); // 逗号分隔，并且增加前缀和后缀
        System.out.println(str3);
    }


    /**
     * 并行流，同时操作集合，速度比串行流更快
     * 多线程执行，CPU消耗较大
     */
    @Test
    public void test13() {
        // 使用串行流
        List<Integer> collect = peples.stream()
                .map(Peple::getAge)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(collect);

        // 使用并行流,CPU会变得较高
        List<Peple> collect1 = peples.parallelStream()
                .sorted(Comparator.comparingInt(Peple::getAge))
                .collect(Collectors.toList());
        System.out.println(collect1);
    }

}
