package com.jdk8.features;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author alan.chen
 * @date 2020/7/10 10:12 PM
 */
public class CompletableFutureTest {

    // 使用ThreadLocal只能当前线程获取数据，异步任务无法获取数据
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    private static InheritableThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<>();


    static class MyThreadPoolExecutor {
        public static ThreadPoolExecutor getExecutor() {
            return new ThreadPoolExecutor(10,
                    200,
                    10,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(1000));
        }
    }



    @Test
    public void runAsAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务1111");
        }, MyThreadPoolExecutor.getExecutor()).whenComplete((res, err) -> {
            System.out.println("res" + res);
            System.out.println("err" + err);
        });


        List<Integer> list = new ArrayList<>();
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务2222");

            list.add(1);
            list.add(3);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
        }, MyThreadPoolExecutor.getExecutor()).thenRunAsync(() -> {
            // 再次开启一个异步任务，等待之前的执行完成才开始执行
            System.out.println("异步任务3333");

            System.out.println("list: " + list.get(0));
            System.out.println("list: " + list.get(1));
        }, MyThreadPoolExecutor.getExecutor());


        // 等待异步任务执行完成再开始执行主线程
        CompletableFuture.allOf(future1, future2).get();
        //if(CompletableFuture.allOf(future1, future2).isDone()) {
        //    System.out.println("------------");
        //}

        System.out.println("main------------------");
    }


    @Test
    public void asyncThreadLocal() throws ExecutionException, InterruptedException {

        System.out.println("main--- threadLocal");
        threadLocal.set(100);

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务1111");

            System.out.println("future1---threadLocal：" + threadLocal.get());
        }, MyThreadPoolExecutor.getExecutor());


        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务2222");

            System.out.println("future2---threadLocal：" + threadLocal.get());
        }, MyThreadPoolExecutor.getExecutor());


        // 等待异步任务执行完成再开始执行主线程
        CompletableFuture.allOf(future1, future2).get();

        System.out.println("main------------------ threadLocal：" + threadLocal.get());
    }

    @Test
    public void asyncThreadLocalShare() throws ExecutionException, InterruptedException {

        System.out.println("main--- asyncThreadLocalShare");
        threadLocal.set(200);

        Integer threadInteger = threadLocal.get();

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务1111");

            // 给当前线程设置保存数据
            threadLocal.set(threadInteger);

            System.out.println("future1---threadLocal：" + threadLocal.get());
        }, MyThreadPoolExecutor.getExecutor());


        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务2222");

            // 给当前线程设置保存数据
            threadLocal.set(threadInteger);

            System.out.println("future2---threadLocal：" + threadLocal.get());
        }, MyThreadPoolExecutor.getExecutor());


        // 等待异步任务执行完成再开始执行主线程
        CompletableFuture.allOf(future1, future2).get();

        System.out.println("main------------------ threadLocal：" + threadLocal.get());
    }


    @Test
    public void asyncInherriableThreadLocal() throws ExecutionException, InterruptedException {

        inheritableThreadLocal.set(300);

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务1111");

            System.out.println("future1---inheritableThreadLocal：" + inheritableThreadLocal.get());
        }, MyThreadPoolExecutor.getExecutor());

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务2222");

            System.out.println("future2---inheritableThreadLocal：" + inheritableThreadLocal.get());
        }, MyThreadPoolExecutor.getExecutor());


        // 等待异步任务执行完成再开始执行主线程
        CompletableFuture.allOf(future1, future2).get();

        System.out.println("main------------------ inheritableThreadLocal：" + inheritableThreadLocal.get());
    }

}
