package com.jdk8.features.pattern.decorator;

/**
 * @author alan.chen
 * @date 2020/7/22 11:01 PM
 */
public class Decorator implements Food {

    private Food food;

    public Decorator(Food food) {
        super();
    }

    @Override
    public String eat() {
        return food.eat();
    }
}
