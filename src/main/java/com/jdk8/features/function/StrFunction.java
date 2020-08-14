package com.jdk8.features.function;

/**
 * 函数式接口，一个类中如果只有一个抽象方法，则该接口为函数式接口
 *
 * @FunctionalInterface 用来校验是否为一个函数式接口（标示以及javadoc中会说明），
 * 如果定义超过1个**抽象方法**则报错，但是可以继续定义抽象方法和静态方法
 *
 * 如果一个接口为函数式接口，则可以通过lambda表达式来创建该接口的对象（如果lambda抛出异常，则该异常需要在接口中抛出声明）
 *
 *
 *
 * @auther alan.chen
 * @time 2019/10/19 11:28 AM
 */
@FunctionalInterface
public interface StrFunction {

    public String getValue(String str);
}
