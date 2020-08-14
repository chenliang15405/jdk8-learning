package com.jdk9.feature;

import java.util.HashMap;

/**
 * @author alan.chen
 * @date 2020/8/6 6:36 PM
 */
public class Main {

    public static void main(String[] args) {

        HashMap<Person, Integer> map = new HashMap<>();

        map.put(new Person("gay伦", 18), 1);
        map.put(new Person("亚瑟", 10), 2);
        map.put(new Person("提莫", 11), 3);

        System.out.println(map);


    }


    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
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
