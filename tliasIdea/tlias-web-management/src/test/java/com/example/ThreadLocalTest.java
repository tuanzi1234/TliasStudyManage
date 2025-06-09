package com.example;

public class ThreadLocalTest {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("hello");
        System.out.println(Thread.currentThread().getName() + ": " +threadLocal.get());
        threadLocal.remove();
        System.out.println(Thread.currentThread().getName() + ": " +threadLocal.get() );
    }
}
