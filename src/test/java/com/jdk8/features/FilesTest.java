package com.jdk8.features;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author alan.chen
 * @date 2020/4/10 5:32 PM
 */
public class FilesTest {


    /**
     * jdk7及以前读取文件方式
     */
    @Test
    public void test1() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(FilesTest.class.getClassLoader().getResourceAsStream("a.txt")));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * jdk7读取文件
     */
    @Test
    public void test2() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FilesTest.class.getClassLoader().getResource("a.txt").getPath()), StandardCharsets.UTF_8);
            System.out.println(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JDK8 读取文件 （stream方式）
     */
    @Test
    public void test3() {
        try {
            // 返回stream流
            Stream<String> stream = Files.lines(Paths.get(FilesTest.class.getClassLoader().getResource("a.txt").getPath()), StandardCharsets.UTF_8);
            stream.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
