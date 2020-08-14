package com.jdk9.feature;

/**
 * @author alan.chen
 * @date 2020/8/5 11:34 PM
 */
public interface PrivateMethod {
    /**
     * 默认方法的访问权限是public，这里的default不是访问修饰符，是关键字
     *
     * @return
     */
    // 接口中的默认方法，访问权限是public
    default String getName() {
        return "111";
    }

    // 接口中的静态方法
    public static void show() {
        System.out.println("123");
    }

    /**
     * jdk9 中接口可以定义私有方法
     */
    //private void methodPrivate() {
    //    System.out.println("JDK 9 新特性：私有方法");
    //}

}
