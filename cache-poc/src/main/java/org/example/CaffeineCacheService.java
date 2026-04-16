package org.example;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.time.Duration;
import java.util.function.Supplier;

public class CaffeineCacheService<T> implements CacheService<String, T> {

  private final Cache<String, T> cache;

  public CaffeineCacheService() {
    this.cache =
        Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(Duration.ofMinutes(5))
            .recordStats()
            .build();
  }

  @Override
  public T get(String key) {
    return cache.getIfPresent(key);
  }

  @Override
  public void put(String key, T o) {
    cache.put(key, o);
  }

  @Override
  public void remove(String key) {
    cache.invalidate(key);
  }

  @Override
  public void clear() {
    cache.invalidateAll();
  }

  @Override
  public T getOrLoad(String key, Supplier<T> loader) {
    return cache.get(key, k -> loader.get());
  }
}
