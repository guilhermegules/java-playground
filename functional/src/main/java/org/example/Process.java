package org.example;

import java.util.function.Function;

public class Process {
    public static int process(int value, String type) {
        if (type.equals("double")) {
            return value * 2;
        }

        if (type.equals("square")) {
            return value * value;
        }

        return value;
    }

    public static int process(int value, Function<Integer, Integer> strategy) {
        return strategy.apply(value);
    }
}
