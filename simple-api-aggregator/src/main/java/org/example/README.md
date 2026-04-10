# Simple API Aggregator

## When to use CompletableFuture

Use CompletableFuture when you want to: Run independent tasks in parallel and combine their results asynchronously

## Use it when

1. Calling multiple APIs, run in parallel, reduce latency
2. I/O bound operations, file reading or in some cases db queries
3. Aggregation / BFF layer
4. Dependent async flows `fetchUser().thenCompose(user -> fetchOrders(user.id())`

## Don't use it when

1. CPU-bound tasks
   1. Encryption
   2. Image processing
   3. Complex algorithms
   4. Use `parallel streams` or `ForkJoinPool`
2. Simple sync logic
3. When the framework already handles async
   1. Spring webflux
   2. Reactive systems

## CompletableFuture vs ExecutorService

| Use case              | Choose              |
|-----------------------|---------------------|
| Fire-and-forget tasks | `ExecutorService`   |
| Need result later     | `Future`            |
| Async composition     | `CompletableFuture` |
