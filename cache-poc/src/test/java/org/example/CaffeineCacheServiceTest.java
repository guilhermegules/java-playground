package org.example;

import org.junit.jupiter.api.BeforeEach;

public class CaffeineCacheServiceTest extends CacheServiceTest {

    private CaffeineCacheService<String> cache;

    @Override
    protected CacheService<String, String> createCache() {
        return cache;
    }

    @BeforeEach
    void setUp() {
        cache = new CaffeineCacheService<>();
        cache.clear();
    }
}
