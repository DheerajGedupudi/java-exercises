package com.amigoscode._7_streams._2_intstreams;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Exercise: IntStream Basics
 *
 * Learn how to use IntStream, a specialized stream for int primitives.
 * IntStream avoids boxing overhead and provides useful numeric operations
 * like sum(), average(), min(), and max().
 */
public class IntStreamBasics {

    public static void main(String[] args) {

        // TODO: 1 - Create an IntStream using IntStream.range(1, 11)
        //           This generates numbers 1 to 10 (end exclusive)
        //           Print each number using forEach
        IntStream.range(1,11).forEach(System.out::println);


        // TODO: 2 - Create an IntStream using IntStream.rangeClosed(1, 10)
        //           Calculate and print the sum of all numbers (should be 55)
        int result2 = IntStream.rangeClosed(1,10).sum();
        System.out.println(result2);


        // TODO: 3 - Create an IntStream.rangeClosed(1, 5) and convert it to
        //           a Stream<Integer> using boxed()
        //           Collect the result to a List<Integer> and print it
        List<Integer> result3 = IntStream.rangeClosed(1,5).boxed().toList();
        System.out.println(result3);


        // TODO: 4 - Create an IntStream using IntStream.of(10, 20, 30, 40, 50)
        //           Print each value
        IntStream.of(10, 20, 30, 40, 50).forEach(System.out::println);


        // TODO: 5 - Create an IntStream of the values 85, 90, 78, 92, 88
        //           Calculate and print the average using .average()
        //           Remember: average() returns OptionalDouble, use getAsDouble() or orElse()
        double result5 = IntStream.of(85, 90, 78, 92, 88).average().orElse(0);
        System.out.println(result5);


        // TODO: 6 - Create an IntStream of the values 15, 3, 42, 7, 28
        //           Find and print both the min and max values
        //           Note: You cannot reuse a stream, so create the IntStream twice
        //           or use summaryStatistics()
        int min = IntStream.of(15, 3, 42, 7, 28).min().orElse(0);
        int max = IntStream.of(15, 3, 42, 7, 28).max().orElse(0);
        System.out.println(min+" "+max);

    }
}
