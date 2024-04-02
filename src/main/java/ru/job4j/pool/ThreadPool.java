package ru.job4j.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int SIZE = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(SIZE);

    public ThreadPool() {
        for (int i = 0; i < SIZE; i++) {
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        System.out.println("Start");
        threadPool.work(() -> System.out.println("Job 1"));
        threadPool.work(() -> System.out.println("Job 2"));
        threadPool.work(() -> System.out.println("Job 3"));
        threadPool.work(() -> System.out.println("Job 4"));
        threadPool.work(() -> System.out.println("Job 5"));
        threadPool.work(() -> System.out.println("Job 6"));
        threadPool.work(() -> System.out.println("Job 7"));
        threadPool.work(() -> System.out.println("Job 8"));
        threadPool.work(() -> System.out.println("Job 9"));
        threadPool.work(() -> System.out.println("Job 10"));
        threadPool.work(() -> System.out.println("Job 11"));
        threadPool.work(() -> System.out.println("Job 12"));
        threadPool.work(() -> System.out.println("Job 13"));
        threadPool.work(() -> System.out.println("Job 14"));
        threadPool.work(() -> System.out.println("Job 15"));
        threadPool.work(() -> System.out.println("Job 16"));
        threadPool.work(() -> System.out.println("Job 17"));
        threadPool.work(() -> System.out.println("Job 18"));
        threadPool.work(() -> System.out.println("Job 19"));
        threadPool.work(() -> System.out.println("Job 20"));
        System.out.println("Stop");
        threadPool.shutdown();
    }

}