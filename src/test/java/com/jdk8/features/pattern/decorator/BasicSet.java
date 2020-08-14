package com.jdk8.features.pattern.decorator;

/**
 * @author alan.chen
 * @date 2020/7/22 11:00 PM
 */
public class BasicSet implements Food {


    @Override
    public String eat() {
        return "吃";
    }
}
