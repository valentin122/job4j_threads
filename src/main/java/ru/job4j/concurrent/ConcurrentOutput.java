package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread first = new Thread(
                ()-> System.out.println(Thread.currentThread().getName())
        );
        for(int i = 0; i <10; i++) {
            Thread third = new Thread(
                    ()-> System.out.println(Thread.currentThread().getName())
            );
            System.out.println(Thread.currentThread().getName());
            third.start();
            System.out.println(Thread.currentThread().getName());
        }
    }
}
