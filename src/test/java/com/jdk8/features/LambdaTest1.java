package com.jdk8.features;

import com.jdk8.features.pojo.Employee;
import com.jdk8.features.function.LongFunction;
import com.jdk8.features.function.StrFunction;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @auther alan.chen
 * @time 2019/10/19 11:18 AM
 */
public class LambdaTest1 {


    List<Employee> list = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 28, 112.33),
            new Employee(103, "王五", 8, 93099.51),
            new Employee(104, "赵六", 38, 313.01),
            new Employee(105, "田七", 58, 3392.00)
    );

    @Test
    public void test1() {
        Collections.sort(list, (e1, e2) -> {
            if(e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        list.forEach(e -> System.out.println(e));
    }


    @Test
    public void test2() {
        String str1 = strHandler("这是一个\t字符串参数后面其实是接口的实现类,\t作为参数传递到函数式接口中", (str) -> str.trim());
        System.out.println(str1);

        String str2 = strHandler("abcdef", str -> str.toUpperCase());
        String str3 = strHandler("acdef", String::toUpperCase);
        System.out.println(str2);
        System.out.println(str3);
    }

    public String strHandler(String str, StrFunction strFunction) {
        return strFunction.getValue(str);
    }



    @Test
    public void test3() {
        Long sum = longHandler(1L, 2L, (l1, l2) -> l1 + l2);
        System.out.println(sum);

        Long sum2 = longHandler(1L, 2L, (l1, l2) -> l1 * l2);
        System.out.println(sum2);
    }

    public Long longHandler(Long l1, Long l2, LongFunction<Long, Long> longFunction) {
        return longFunction.getValue(l1, l2);
    }


}
