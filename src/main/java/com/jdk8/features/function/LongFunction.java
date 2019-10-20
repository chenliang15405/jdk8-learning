package com.jdk8.features.function;

/**
 * @auther alan.chen
 * @time 2019/10/20 10:54 AM
 */
@FunctionalInterface
public interface LongFunction<T, R> {

    public R getValue(T t1, T t2);

}
