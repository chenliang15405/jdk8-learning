package com.jdk8.features;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author alan.chen
 * @date 2020/4/14 10:11 PM
 */
public class RemoveIfTest {

    private List<String> list = Stream.of("a", "b", "c", "d", "e", "f").collect(Collectors.toList());


    /**
     * Jdk8之前，list遍历无法直接删除元素，否则会直接报错：ConcurrentModificationException，因为
     * 在for循环遍历的时候，会维护一个期待的列表长度值，每次遍历的时候，都会和当前的列表值进行比较，如果不一致，则报错
     *
     * 所以，在遍历中删除数据只有通过Iterator的方式，通过Iterator删除元素
     *
     */
    @Test
    public void listRemove() {
        System.out.println("删除之前");
        list.forEach(System.out::println);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().equals("d")) {
                iterator.remove();
            }
        }
        System.out.println("删除之后");
        list.forEach(System.out::println);
    }

    /**
     * Jdk8删除元素, removeIf在内部也是通过iterator来实现
     */
    @Test
    public void listRemoveIf() {
        System.out.println("删除之前");
        list.forEach(System.out::println);

        list.removeIf(item -> item.equals("d"));

        System.out.println("删除之后");
        list.forEach(System.out::println);
    }

    /**
     * Arrays.asList 返回的是Arrays的内部类ArrayList，只能查询，不能其他操作，没有实现
     */
    @Test
    public void arraysRemoveIf() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");

        System.out.println("删除之前");
        list.forEach(System.out::println);

        list.removeIf(item -> item.equals("d"));

        System.out.println("删除之后");
        list.forEach(System.out::println);
    }

}
