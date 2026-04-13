package com.example;

public class Task {
    private final int id;
    private final String data;
    private final long timestamp;

    public Task(int id) {
        this.id = id;
        this.data = "Task-" + id;
        this.timestamp = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", data='" + data + "'}";
    }
}
