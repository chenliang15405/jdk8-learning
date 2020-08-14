package com.jdk8.features;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author alan.chen
 * @date 2020/6/23 6:32 PM
 */
public class comparator {

    @Test
    public void test() {
        TreeMap<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 如果o1-o2为负数，则o1在o2前，为正数则o2在o2前
                return o1 - o2;
            }
        });

        map.put(2, 1);
        map.put(5, 5);
        map.put(1, 1);
        map.put(3, 3);

        System.out.println(map);
    }


    @Test
    public void sortlambda() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("一", 20));
        list.add(new Person("二", 25));
        list.add(new Person("三", 10));
        list.add(new Person(null, 0));
        list.add(new Person("五", 30));

        List<Person> result = list.stream().filter(item -> {
            return item.name != null;
        }).sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());

        List<Person> result1 = list.stream().filter(item -> {
            return item.name != null;
        }).sorted((a, b) -> a.getAge() - b.getAge()).collect(Collectors.toList());

        result1.forEach(System.out::println);
    }


    class Person {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
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
