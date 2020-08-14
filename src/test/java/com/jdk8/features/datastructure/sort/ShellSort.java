package com.jdk8.features.datastructure.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author alan.chen
 * @date 2020/5/27 10:35 PM
 */
public class ShellSort {

    private static int[] arr = {50, 3, 22, 99, 90, 567, 999, 66, 123, 9};


    @Test
    public void test() {
        int gap = arr.length / 2;

        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int preIndex = i - gap;
                int cur = arr[i];
                while (preIndex >= 0 && arr[preIndex] >  cur) {
                    swap(arr, preIndex, preIndex + gap);
                    preIndex -= gap;
                }
            }
            gap /= 2;
        }

        print();
    }


    public static void print() {
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
