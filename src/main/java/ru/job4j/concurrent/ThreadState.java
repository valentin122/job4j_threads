package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                ()-> {}
        );
        Thread second = new Thread(
                ()-> {}
        );
        System.out.println(first.getName() + first.getState());
        System.out.println(second.getName() + second.getState());
        first.start();
        second.start();
        int count = 0;
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getName() + first.getState() + count);
            System.out.println(second.getName() + second.getState() + count);
            count++;
        }
        System.out.println(first.getName() + first.getState());
        System.out.println( second.getName() + second.getState());
        System.out.println("Работа завершена.");

    }
}
