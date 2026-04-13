# Rate Limiter (Token Bucket)

A Java implementation of the Token Bucket algorithm for rate limiting.

## What is this?

A rate limiter controls how many requests can be processed per second. This is essential for:

- API protection
- Preventing abuse
- Cloud service cost control

## Token Bucket Algorithm

1. **Bucket** holds tokens (max = capacity)
2. **Tokens refill** at a fixed rate
3. **Each request** consumes 1 token
4. **Request rejected** if no tokens available

## Key Concepts

### Semaphore

Used to track available permits (tokens) - provides thread-safe access.

### Backpressure

When tokens are depleted, requests are rejected instead of overwhelming the system.

### Real-time Refill

Tokens are added continuously based on the refill rate.

## How to Run

```bash
cd rate-limiter
javac -d target/classes src/main/java/com/example/*.java
java -cp target/classes com.example.Main
```

## Code Structure

| Class                    | Role                                       |
| ------------------------ | ------------------------------------------ |
| `TokenBucketRateLimiter` | Rate limiting logic with token bucket      |
| `Main`                   | Demo with multiple threads making requests |

## Real-World Usage

- **API Gateways** (Kong, AWS API Gateway)
- **Load balancers**
- **Cloud functions** (AWS Lambda, GCP Cloud Functions)
- **Token servers** (OAuth rate limits)
