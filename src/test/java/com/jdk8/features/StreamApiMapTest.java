package com.jdk8.features;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  jdk8 遍历Map方式
 *
 * @author alan.chen
 * @date 2020/5/1 9:37 AM
 */
public class StreamApiMapTest {


    /**
     * foreach直接遍历
     */
    @Test
    public void test1() {
        Map<String, Integer> map = new HashMap<>();
        map.put("一", 1);
        map.put("二", 2);
        map.put("三", 3);

        map.forEach((k, v) -> {
            System.out.println("key = " + k + " value=" + v);
        });
    }

    /**
     * 获取Set<Key>然后遍历key集合获取value
     */
    @Test
    public void test2() {
        Map<String, Integer> map = new HashMap<>();
        map.put("一", 1);
        map.put("二", 2);
        map.put("三", 3);

        map.keySet()
                .forEach(k -> {
                    System.out.println("key = " + k + " value=" + map.get(k));
                });
    }

    /**
     * 获取entry对象，遍历entry
     */
    @Test
    public void test3() {
        Map<String, Integer> map = new HashMap<>();
        map.put("一", 1);
        map.put("二", 2);
        map.put("三", 3);

        // 其实和第一种一样
        map.entrySet()
                .forEach((entry) -> {
                    System.out.println("key = " + entry.getKey() + " value=" +entry.getValue());

                });
    }

    @Test
    public void test5() {
        Map<String, List<User>> map = new HashMap<>();
        List<User> list1 = new ArrayList<>();
        list1.add(new User("小明", "战神"));
        list1.add(new User("小王", "神之子"));
        map.put("一", list1);

        List<User> list2 = new ArrayList<>();
        list2.add(new User("小张", "英雄"));
        list2.add(new User("小张", "龙的传人"));
        map.put("二", list2);

        Map<String, String> cookieMap = new HashMap<>();

        // 将map集合中的Value数据复制到cookieMap
        // 只复制Value

        map.entrySet()
                .stream()
                .map(Map.Entry::getValue)  // Map<String, List<User>> -> List<User>
                .flatMap(List<User>::stream)  // List<User> -> User的stream对象   flatMap相当于展开集合
                .forEach(user -> {
                    cookieMap.put(user.getUsername(), user.getPassword());
                });

        cookieMap.forEach((k, v) -> {
            System.out.println("key = " + k + " value=" + v);
        });

    }

    @Test
    public void test6() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("11");
        map.put("一", list1);

        List<String> list2 = new ArrayList<>();
        list2.add("2");
        list2.add("22");
        map.put("二", list2);

        Map<String, String> cookieMap = new HashMap<>();

        // 将map集合数据复制到cookieMap
        // 将key复制为cookieMap的key， value为list，合并为String然后保存到另一个map

        map.entrySet()
                .stream()
                .forEach(entry -> {
                    cookieMap.put(entry.getKey(),
                                  entry.getValue().stream().collect(Collectors.joining())
                                );
                });

        cookieMap.forEach((k, v) -> {
            System.out.println("key = " + k + " value=" + v);
        });

    }


    class User {
        private String username;
        private String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
