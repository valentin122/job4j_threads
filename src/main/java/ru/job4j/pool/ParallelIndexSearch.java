package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T searchObj;
    private final int from;
    private final int to;

    public ParallelIndexSearch(T[] array, T searchObj, int from, int to) {
        this.array = array;
        this.searchObj = searchObj;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from < 10) {
            return simpleFind();
        }
        int middle = (from + to) / 2;

        ParallelIndexSearch<T> leftSort = new ParallelIndexSearch<>(array, searchObj, from, middle);
        ParallelIndexSearch<T> rightSort = new ParallelIndexSearch<>(array, searchObj, middle + 1, to);

        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return Math.max(left, right);
    }

    private int simpleFind() {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(searchObj)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public int findIndex(T[] array, T searchObj) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, searchObj, 0, array.length));
    }
}
