package ru.job4j.concurrent;

public final class NodeImmutable<T> {
    private final NodeImmutable<T> next;
    private final T value;

    public NodeImmutable(NodeImmutable<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public NodeImmutable<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }

}
