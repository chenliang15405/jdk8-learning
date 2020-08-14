package com.jdk9.feature;

import java.io.*;

/**
 * @author alan.chen
 * @date 2020/8/5 11:42 PM
 */
public class ResouceClosable {

    public static void main(String[] args) throws FileNotFoundException {

        /**
         * java8的资源关闭，定义在try括号中
         */
        try(InputStreamReader in = new InputStreamReader(System.in)) {
            int read = in.read();
            System.out.println(read);
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         * java9 资源关闭的操作，需要关闭的资源的实例化可以定义在try的小括号的外面，
         *
         * 声明在外面的资源对象是常量，默认为final修饰，不可修改
         *
         */
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(""));
        //try(reader; writer) {
        //    int read = reader.read();
        //    System.out.println(read);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

    }
}
