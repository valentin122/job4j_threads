package ru.job4j.concurrent;

public final class Cache {
    private static volatile Cache cache;
    private static final Object LOCK = new Object();

    public static synchronized Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
