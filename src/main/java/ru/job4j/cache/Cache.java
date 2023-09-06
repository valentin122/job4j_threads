package ru.job4j.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final ConcurrentHashMap<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        BiFunction<Integer, Base, Base> biFunc = (keyCache, modelCache) -> {
            if (modelCache.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base base = new Base(model.getId(), model.getVersion() + 1);
            base.setName(model.getName());
            return base;
        };
        return memory.computeIfPresent(model.getId(), biFunc) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Map<Integer, Base> getMemory() {
        return new HashMap<>(memory);
    }
}