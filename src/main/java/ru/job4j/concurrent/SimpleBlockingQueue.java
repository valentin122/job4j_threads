package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private final Object monitor = this;
    private int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() >= limit) {
            try {
                monitor.wait();
            } finally {

            }
        }
        queue.add(value);
        monitor.notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        T element;
        while (queue.isEmpty()) {
            try {
                monitor.wait();
            } finally {

            }
        }
        element = queue.poll();
        monitor.notifyAll();
        return element;
    }

}
