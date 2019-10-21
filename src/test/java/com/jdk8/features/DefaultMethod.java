package com.jdk8.features;

/**
 * 关于接口的默认方法：
 *
 *   如果一个类同时实现该接口和集成一个类，那么会遵从"类优先"原则，调用父类的方法是class类中定义的方法
     如果一个接口中定义了一个默认方法，另一个接口中也有一个相同的方法（无论是否是默认方法），实现类必须实现该方法并指定实现那个接口的方法
 */
public interface DefaultMethod {


    // 接口中的默认方法
    default String getName() {
        return "111";
    }

    // 接口中的静态方法
    public static void show() {
        System.out.println("123");
    }

}
