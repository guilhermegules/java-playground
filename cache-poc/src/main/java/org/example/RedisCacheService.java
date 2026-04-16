package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class RedisCacheService<T> implements CacheService<String, T> {

  private final RedisCommands<String, String> commands;
  private final ObjectMapper mapper = new ObjectMapper();
  private final Class<T> type;
  private final ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();

  public RedisCacheService(String redisUrl, Class<T> type) {
    this.type = type;

    RedisClient client = RedisClient.create(redisUrl);
    StatefulRedisConnection<String, String> connection = client.connect();
    this.commands = connection.sync();
  }

  @Override
  public T get(String key) {
    String value = commands.get(key);
    if (value == null) return null;

    return deserialize(value, type);
  }

  @Override
  public void put(String key, T value) {
    commands.set(key, serialize(value));
  }

  @Override
  public void remove(String key) {
    commands.del(key);
  }

  @Override
  public void clear() {
    commands.flushall();
  }

  @Override
  public T getOrLoad(String key, Supplier<T> loader) {
    String value = commands.get(key);

    if (value != null) {
      return deserialize(value, type);
    }

    Object lock = locks.computeIfAbsent(key, k -> new Object());

    synchronized (lock) {
      try {
        value = commands.get(key);
        if (value != null) {
          return deserialize(value, type);
        }

        T loaded = loader.get();
        commands.setex(key, 300, serialize(loaded));

        return loaded;
      } finally {
        locks.remove(key);
      }
    }
  }

  private String serialize(T value) {
    try {
      return mapper.writeValueAsString(value);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private T deserialize(String value, Class<T> type) {
    try {
      return mapper.readValue(value, type);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
