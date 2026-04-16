package org.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class CacheServiceTest {

    protected abstract CacheService<String, String> createCache();

    @Test
    void shouldPutAndGet() {
        var cache = createCache();

        cache.put("key", "value");

        assertThat(cache.get("key")).isEqualTo("value");
    }

    @Test
    void shouldReturnNullWhenMissing() {
        var cache = createCache();

        assertThat(cache.get("missing")).isNull();
    }

    @Test
    void shouldRemoveValue() {
        var cache = createCache();

        cache.put("key", "value");
        cache.remove("key");

        assertThat(cache.get("key")).isNull();
    }

    @Test
    void shouldClearCache() {
        var cache = createCache();

        cache.put("k1", "v1");
        cache.put("k2", "v2");

        cache.clear();

        assertThat(cache.get("k1")).isNull();
        assertThat(cache.get("k2")).isNull();
    }

    @Test
    void shouldLoadValueOnMiss() {
        var cache = createCache();

        var value = cache.getOrLoad("key", () -> "loaded");

        assertThat(value).isEqualTo("loaded");
    }

    @Test
    void shouldCacheLoadedValue() {
        var cache = createCache();

        AtomicInteger counter = new AtomicInteger();

        cache.getOrLoad("key", () -> {
            counter.incrementAndGet();
            return "value";
        });

        cache.getOrLoad("key", () -> {
            counter.incrementAndGet();
            return "value";
        });

        assertThat(counter.get()).isEqualTo(1);
    }

    @Test
    void shouldHandleConcurrentAccess() throws InterruptedException {
        var cache = createCache();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger counter = new AtomicInteger();

        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                cache.getOrLoad("key", () -> {
                    counter.incrementAndGet();
                    return "value";
                });
                latch.countDown();
            });
        }

        latch.await();

        assertThat(counter.get()).isEqualTo(1);
    }
}
