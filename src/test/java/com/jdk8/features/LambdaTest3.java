package com.jdk8.features;

import com.jdk8.features.pojo.Employee;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用： 若lambda体中的内容有方法已经实现了，我们可以使用"方法引用"
 *          （可以理解为方法引用是lambda表达式的另一种表现形式）
 *
 *  主要有三种语法格式：
 *
 *   对象::实例方法名
 *   类::静态方法名
 *   类::实例方法名
 *
 * 注意：
 *   1. Lambda体中调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的函数列表和返回类型保持一致
 *   2. 若Lambda 参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用 ClassName::method
 *
 *
 * 二、构造器引用
 *
 *  格式：
 *   ClassName::new
 *
 *  注意：
 *      需要调用的参数列表和构造器中的参数列表的个数需要保持一致
 *
 *
 * 三、数组引用
 *
 *  格式：
 *      Type::new
 *
 *
 */
public class LambdaTest3 {

    @Test
    public void test1() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("123");
    }

    // 对象::实例方法
    @Test
    public void tet2() {
        Employee employee = new Employee();
        Supplier<String> supplier = () -> employee.getName();
        System.out.println(supplier.get());

        Supplier<Integer> supplier1 = employee::getAge;
        System.out.println(supplier.get());
    }

    // 类::静态方法
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> x.compareTo(y);

        // 方法引用
        // 注意的是，方法体中的方法的参数和返回值的类型需要和函数式接口的类型一致才可以使用方法引用，相当于默认的约定参数
        Comparator<Integer> comparator1 = Integer::compare;
        System.out.println(comparator1.compare(2, 3));
    }

    //类::实例方法名
    // 使用类调用实例方法，需要第一个参数是这个方法的调用者，第二个参数是这个方法的参数，这样才可以使用
    @Test
    public void test4() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> biPredicate1 = String::equals;
        System.out.println(biPredicate1.test("1", "1"));
    }

    @Test
    public void test5() {
        Supplier<Employee> supplier = () -> new Employee();

        // 构造器引用
        // 调用的是无参数构造器还是有参数构造器，自动匹配的是函数式接口的参数列表的构造器，如果函数式接口的方法没有参数，
        // 则调用无参构造，如果有参数，则调用指定参数个数的构造器
        Supplier<Employee> supplier1 = Employee::new;
        System.out.println(supplier1.get());

        Function<Integer, Employee> function = Employee::new;
        System.out.println(function.apply(100));
    }


    // Type::new
    @Test
    public void test6() {
        Function<Integer, String[]> function = (x) -> new String[x];
        System.out.println(function.apply(10).length);

        Function<Integer, String[]> function1 = String[]::new;
        System.out.println(function1.apply(20).length);
    }


}
