package com.jdk8.features;

import org.junit.Test;

import java.util.Random;

/**
 * @author alan.chen
 * @date 2020/7/12 9:14 PM
 */
public class TestDemo {

    public static void main(String[] args) {

        System.out.println((int)Math.ceil(1.0 / 500));
        String str = "100,500,300,200,400,100";

        String[] arr = str.split(",");
        
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += Double.valueOf(arr[i]);
        }
        int num = (int)Math.ceil(sum / 500);
        System.out.println(num);

        // jdk8 新特性生成随机数
        int asInt = new Random().ints(10, 100).findFirst().getAsInt();
        System.out.println(asInt);

    }

}