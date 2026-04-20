package org.example;

import java.util.function.Function;

public class Fn {
    public static <T, R> Function<T, R> pipe(Function<T, R> fn) {
        return fn;
    }

    public static <T, R, V> Function<T, V> pipe(Function<T, R> fn1, Function<R, V> fn2) {
        return fn1.andThen(fn2);
    }
}
