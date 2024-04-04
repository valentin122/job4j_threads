package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.*;

public class ParallelIndexSearchTest {
    @Test
    public void whenIndex0AndLengthMore10Then() {
        Integer[] array = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        ParallelIndexSearch<Integer> parallelIndexSearch =
                new ParallelIndexSearch<>(array, 0, 0, array.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = forkJoinPool.invoke(parallelIndexSearch);
        int expected = 0;
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void whenIndex10AndLengthMore10Then() {
        Integer[] array = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        ParallelIndexSearch<Integer> parallelIndexSearch =
                new ParallelIndexSearch<>(array, 11, 0, array.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = forkJoinPool.invoke(parallelIndexSearch);
        int expected = 11;
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void whenLastIndexAndLengthMore10Then() {
        Integer[] array = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        ParallelIndexSearch<Integer> parallelIndexSearch =
                new ParallelIndexSearch<>(array, 14, 0, array.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = forkJoinPool.invoke(parallelIndexSearch);
        int expected = 14;
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void whenFirstIndexAndLengthLess10Then() {
        Integer[] array = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
        ParallelIndexSearch<Integer> parallelIndexSearch =
                new ParallelIndexSearch<>(array, 0, 0, array.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = forkJoinPool.invoke(parallelIndexSearch);
        int expected = 0;
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void whenLastIndexAndLengthLess10Then() {
        Integer[] array = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
        ParallelIndexSearch<Integer> parallelIndexSearch =
                new ParallelIndexSearch<>(array, 8, 0, array.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = forkJoinPool.invoke(parallelIndexSearch);
        int expected = 8;
        assertThat(expected).isEqualTo(result);
    }

}