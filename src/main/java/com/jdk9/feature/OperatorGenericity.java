package com.jdk9.feature;

import java.util.Comparator;

/**
 * @author alan.chen
 * @date 2020/8/5 11:35 PM
 */
public class OperatorGenericity {

    public static void main(String[] args) {

        /**
         * 钻石操作符，后面的泛型可以自动根据前面的推断，在jdk9之前不可以
         *
         * jdk9之前可以对集合进行推断，但是无法对带有匿名内部类的泛型进行推断
         *
         */
        //Comparator<String> com = new Comparator<>() {
        //    @Override
        //    public int compare(String o1, String o2) {
        //        return 0;
        //    }
        //};

        /**
         * String的实现由 char[] 变为 byte[]
         */

    }

}
