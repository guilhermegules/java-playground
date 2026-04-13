# Producer-Consumer Pattern

A Java demonstration of the classic producer-consumer pattern using `BlockingQueue`.

## What is this?

The producer-consumer pattern is a classic concurrency problem where:

- **Producer**: Generates tasks and adds them to a shared queue
- **Consumer**: Retrieves tasks from the queue and processes them

## Key Concepts

### BlockingQueue

A thread-safe queue that provides blocking operations:

- `put()` - Blocks if queue is full (backpressure)
- `take()` - Blocks if queue is empty

### Backpressure

When the queue is full, the producer **waits** instead of overwhelming the system. This prevents memory issues when producers are faster than consumers.

### Thread Communication

Threads don't need explicit synchronization - the queue handles all the coordination.

## How to Run

```bash
cd producer-consumer
javac -d target/classes src/main/java/com/example/*.java
java -cp target/classes com.example.Main
```

## Code Structure

| Class      | Role                                   |
| ---------- | -------------------------------------- |
| `Task`     | Represents a unit of work              |
| `Producer` | Generates tasks, adds to queue         |
| `Consumer` | Takes tasks from queue, processes them |

## Real-World Usage

This pattern is used in:

- **Kafka** - Message queues
- **Thread pools** - Work distribution
- **Event-driven systems** - Async task processing
