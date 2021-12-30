package ru.job4j.concurrent;

public final class Cache {
    private static volatile Cache cache;
    private static final Object LOCK = new Object();

    public synchronized static Cache instOf() {
        Cache c = cache;
        if (c == null) {
            synchronized (LOCK) {
                c = cache;
                if (c == null) {
                    c = new Cache();
                    cache = c;
                }
            }
        }
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
