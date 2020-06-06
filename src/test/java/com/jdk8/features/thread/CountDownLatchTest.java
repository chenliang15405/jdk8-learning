package com.jdk8.features.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author alan.chen
 * @date 2020/5/20 6:23 PM
 */
public class CountDownLatchTest {


    /**
     * CountDownLatch是通过多线程中调用countDown的方式减计数，多线程中调用countDown后继续执行，
     * 主线程等待，等待计数等于0的时候，等待的线程开始执行
     *
     *  CountDownLatch只会使用一次
     */
    @Test
    public void test1() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            int index = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(index * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                System.out.println("第" + index + "位执行ok!");
            });
        }

        latch.await();

        System.out.println("一起执行");
    }

    /**
     * CyclicBarrier 是通过多线程中让每个线程等待，当所有的线程都处于等待的时候，同时开始执行所有线程
     *
     * CyclicBarrier可以重复使用
     *
     */
    @Test
    public void test2() throws InterruptedException, BrokenBarrierException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CyclicBarrier barrier = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            int index = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(index * 1000);
                    System.out.println(index + " 等待");
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("第" + index + "位执行ok!");
            });
        }

        while (true) {

        }
        //System.out.println("一起执行");
    }

}
