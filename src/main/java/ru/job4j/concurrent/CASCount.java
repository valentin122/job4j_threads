package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer oldValue, newValue;
        do {
            oldValue = count.get();
            /*if (oldValue == null) {
                oldValue = 0;
            }*/
            newValue = oldValue + 1;
        } while (!count.compareAndSet(oldValue, newValue));

        //throw new UnsupportedOperationException("Count is not impl.");
    }

    public int get() {
        Integer result = count.get();
        if (result == null) {
            throw new UnsupportedOperationException("smth go wrong...");
        }
        return result;
    }
}
