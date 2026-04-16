package org.example;


import org.junit.jupiter.api.BeforeEach;

public class RedisCacheServiceTest extends CacheServiceTest {
    private RedisCacheService<String> cache;

    @Override
    protected CacheService<String, String> createCache() {
        return cache;
    }

    @BeforeEach
    void setUp() {
        cache = new RedisCacheService<>("redis://localhost:6379", String.class);
        cache.clear();
    }
}
