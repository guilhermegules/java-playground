package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    static void main() {
        int result = applyOperation(5, x -> x * 2);
        System.out.println(result);

        Function<Integer, Integer> doubler = createMultiplier(2);
        System.out.println(applyOperation(5, doubler));

        Function<String, String> trim = String::trim;
        Function<String, String> toUpper = String::toUpperCase;
        Function<String, String> pipeline = trim.andThen(toUpper);
        System.out.println(pipeline.apply("Hello java functional"));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Predicate<Integer> isEven = x -> x % 2 == 0;

        numbers.stream().filter(isEven).forEach(System.out::println);

        String data = withLog(() -> "Hello high order utility");
        System.out.println(data);

        Function<Integer, Integer> add2 = x -> x + 2;
        Function<Integer, Integer> multiply3 = x -> x * 3;
        Function<Integer, Integer> pipe = add2.andThen(multiply3);
        System.out.println(pipe.apply(2));

        System.out.println(Process.process(5, "double"));
        System.out.println(Process.process(5, x -> x * 2));

        System.out.println(withTime(() -> 1 + 3));

        List<String> names = List.of("a", "b", "c", "d", "e", "f");
        List<String> result2 = names.stream().map(String::toUpperCase).toList();
        System.out.println(result2);

        Function<Integer, Integer> pipe2 = Fn.pipe(x -> x * 2, x -> x + 5);
        System.out.println(pipe2.apply(5));
    }

    static int applyOperation(int value, Function<Integer, Integer> fn) {
        return fn.apply(value);
    }

    static Function<Integer, Integer> createMultiplier(int factor) {
        return x -> x * factor;
    }

    static <T> T withLog(Supplier<T> fn) {
        System.out.println("Starting");
        T result = fn.get();
        System.out.println("Ending");
        return result;
    }

    static <T> T withTime(Supplier<T> action) {
        long start = System.currentTimeMillis();
        T result = action.get();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + "ms");
        return result;
    }
}
