package com.jdk8.features;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置四大核心函数式接口
 *
 *  Consumer<T> : 消费型接口
 *      void accept(T t);
 *
 *  Supplier<T>: 供给型接口
 *      T get();
 *
 *  Function<T, R> 函数型接口
 *      R apply(T t);
 *
 *  Predicate<T>: 断言型接口
 *      boolean test(T t)
 *
 */
/**
 *
 * 除了上面的四个核心的函数式接口，还有下面的其他函数式接口可以使用，分别是上面那些接口的子接口
 *
 * BiFunction<T, U, R>  参数类型：T, U  返回类型： R      R apply(T t, U u)
 *
 * UnaryOperator<T>  参数类型：T  返回类型： T        T apply(T t)
 *
 * BinaryOperator<T>  参数类型：T, T  返回类型： T    T apply(T t1, T t2)
 *
 * BiConsumer<T, U>  参数类型：T, U  返回类型： void   void accept(T t, U u)
 *
 * ToIntFunction<T>  参数类型：T  返回类型： int
 * ToLongFunction<T>  参数类型：T  返回类型： long
 * toDoubleFunction<T>  参数类型：T  返回类型： double
 *
 * IntFunction<R>  参数类型：int  返回类型： R
 * LongFunction<R>  参数类型：long  返回类型： R
 * DoubleFunction 参数类型：double  返回类型： R
 */
public class LambdaTest2 {


    @Test
    public void testConsumer() {
        handlerConsumer("123", str -> System.out.println(str));
    }

    public void handlerConsumer(String str, Consumer<String> consumer) {
        consumer.accept(str);
    }

    @Test
    public void testSupplier() {
        List<Integer> list = handlerSupplier(10, () -> (int) (Math.random() * 100));
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }

    public List<Integer> handlerSupplier(Integer num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    @Test
    public void testFuction() {
        String str1 = handlerFunction("\tfunction的函数式接口\tjdk内置", str -> str.trim());
        System.out.println(str1);

        String str2 = handlerFunction("只能返回指定的类型", str -> str.substring(0, 3));
        System.out.println(str2);
    }

    public String handlerFunction(String str, Function<String, String> function) {
        return function.apply(str);
    }

    @Test
    public void testPredicate() {
        List<String> list = Arrays.asList("123", "321", "这是一个数据", "2", "1", "数据");
        List<String> newList = handlerPredicate(list, str -> str.length() > 2);
        for (String s : newList) {
            System.out.println(s);
        }
    }

    public List<String> handlerPredicate(List<String> list, Predicate<String> predicate) {
        List<String> newList = new ArrayList<>();

        for (String s : list) {
            if(predicate.test(s)) {
                newList.add(s);
            }
        }
        return newList;
    }


}
