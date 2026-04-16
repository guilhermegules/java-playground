package org.example;

import java.util.function.Supplier;

public interface CacheService<Key, Value> {
    Value get(Key key);
    void put(Key key, Value value);
    void remove(Key key);
    void clear();
    Value getOrLoad(Key key, Supplier<Value> loader);
}
