# Java Cache PoC Caffeine vs Redis

This project is a deep Proof of Concept (PoC) exploring caching strategies in Java using:

- In-memory cache with Caffeine
- Distributed cache with Redis

The goal is to compare behavior, performance, and trade-offs between local and distributed caching approaches, including concurrency handling and cache consistency.

## Objectives

- Implement a cache abstraction layer
- Provide two interchangeable cache implementations
- Support object serialization
- Handle cache stampede scenarios
- Validate behavior with unit and concurrency tests
- Prepare for benchmarking and metrics analysis

## Architecture

```
                +------------------+
                |   Application    |
                +------------------+
                          |
                          v
                +------------------+
                |  CacheService<T> |
                +------------------+
                   /            \
                  /              \
                 v                v
     +------------------+   +------------------+
     |   Caffeine Cache |   |   Redis Cache    |
     +------------------+   +------------------+
```

## Caffeine Implementation

The in-memory cache uses Caffeine.

### Key Features

- Extremely low latency (in-process)
- Built-in atomic loading
- Automatic cache stampede protection
- No serialization overhead


### Behavior

```
cache.get(key, k -> loader.get());
``` 

- Guarantees only one thread computes the value
- Other threads wait for the result

## Redis Implementation

The distributed cache uses Redis with the Lettuce client.

### Object Support

Since Redis stores strings, objects are serialized using JSON:

- Serialization: Object → JSON
- Deserialization: JSON → Object

Handled via Jackson with ObjectMapper.

## Cache-Aside Pattern

```java
T value = cache.get(key);

if (value == null) {
    value = loader.get();
    cache.put(key, value);
}

return value;
```

## Cache Stampede Problem

Unlike Caffeine, Redis does not provide built-in protection.

### Problem

Multiple threads:

```
Thread A -> cache miss -> load
Thread B -> cache miss -> load
Thread C -> cache miss -> load
```

Causes duplicated work also cache stampede

### Solution: Local Locking

```java
synchronized (lock) {
    // double-check cache
    // load only once
}
```

**Result**

- Prevents duplicate loads within a single instance
- Makes Redis behavior consistent with Caffeine in tests

### Limitation

This solution is not distributed-safe.

If multiple instances exist:

```
Instance A -> load
Instance B -> load
```

Still duplicated

### Key differences

| Feature             | Caffeine        | Redis             |
| ------------------- |-----------------| ----------------- |
| Latency             | Very low        | Network-dependent |
| Scope               | Local           | Distributed       |
| Serialization       | Not required    | Required          |
| Stampede protection | Built-in        | Manual            |
| Scalability         | Single instance | Multi-instance    |

## Insights

1. In-memory vs Distributed Trade-off
   Caffeine is faster but limited to a single instance
   Redis enables sharing across systems but adds complexity
2. Concurrency is a first-class concern
   In-memory caches handle it internally
   Distributed caches require explicit coordination
3. Serialization matters
   Adds latency and CPU overhead
   Must be considered in performance analysis
4. Cache design is not trivial

Even a “simple cache” requires handling:

- Concurrency
- Consistency
- Failures
- Expiration policies
