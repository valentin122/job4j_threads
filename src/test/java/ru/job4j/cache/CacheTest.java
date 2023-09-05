package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


import java.util.Map;

class CacheTest {

    @Test
    void whenAdd() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 2);
        assertThat(cache.add(base1)).isTrue();
        assertThat(cache.add(base1)).isFalse();
        assertThat(cache.add(base2)).isTrue();
        assertThat(cache.getMemory()).isEqualTo(Map.of(1, base1, 2, base2));
    }

    @Test
    void whenUpdate() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 2);
        Base base3 = new Base(1, 1);
        cache.add(base1);
        cache.add(base2);
        assertThat(cache.update(base3)).isTrue();
        assertThat(cache.update(new Base(3, 3))).isFalse();
        assertThat(cache.getMemory().get(base1.getId()).getVersion()).isEqualTo(2);
    }

    @Test
    void whenUpdateCheckName() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base3 = new Base(1, 1);
        base3.setName("jobisdone");
        cache.add(base1);
        cache.update(base3);
        assertThat(cache.getMemory().get(base1.getId()).getName()).isEqualTo("jobisdone");
    }

    @Test
    void whenDelete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 1);
        Base base3 = base2;
        cache.add(base1);
        cache.add(base2);
        base1 = new Base(1, 2);
        cache.delete(base1);
        assertThat(cache.getMemory()).containsEntry(2, base3);
    }

    @Test
    void whenUpdateThenException() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        cache.add(base1);
        assertThatThrownBy(() -> cache.update(new Base(1, 11)))
                .isInstanceOf(OptimisticException.class)
                .message()
                .isNotEmpty();
    }

}