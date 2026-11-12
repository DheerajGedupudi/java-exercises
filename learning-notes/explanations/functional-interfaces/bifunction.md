# BiFunction: two inputs and one result

[Home](../../README.md) · Previous: [Function](function.md) · Next: [Predicate](predicate.md)

## Purpose and contract

`BiFunction<T, U, R>` is a standard library functional interface from
`java.util.function`. It is a type name, not a Java keyword. `Bi` refers
to two inputs.

Its abstract method is:

```java
R apply(T first, U second);
```

| Type argument | Meaning in `BiFunction<Integer, Integer, Integer>` |
|---|---|
| `T` | First input, such as `a` |
| `U` | Second input, such as `b` |
| `R` | Result, such as their sum |

There are two method parameters. The third generic argument describes
the return type. Java's library already declares this interface, just as
you declared your own `Greeting` interface earlier.

## Syntax and implicit return

These alternative declarations supply the same addition behavior:

```java
BiFunction<Integer, Integer, Integer> sum = (Integer a, Integer b) -> {
    int result = a + b;
    return result;
};
```

```java
BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
```

The first body uses a block with an explicit return. The second supplies
the expression's value directly. The surrounding target lets Java infer
the two parameter types.

`sum` is behavior. `sum.apply(3, 4)` invokes it and returns `7`.
That is the same distinction between a function variable and its result
that you saw with [Function](function.md).

## A complete example: a result passed to another function

```java
import java.util.function.BiFunction;
import java.util.function.Function;

public class BiFunctionDemo {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        BiFunction<String, String, String> join = (left, right) -> left + "/" + right;
        Function<String, Integer> size = String::length;
        BiFunction<String, String, Integer> joinedSize = join.andThen(size);

        System.out.println(sum.apply(3, 4));
        System.out.println(join.apply("ab", "c"));
        System.out.println(joinedSize.apply("ab", "c"));
    }
}
```

Output:

```text
7
ab/c
4
```

The composed function still takes two original arguments. The second
function takes just one argument: the result from `join`.

```text
two strings → join → one string → size → one integer
```

## andThen builds behavior before apply runs it

`join.andThen(size)` returns a new `BiFunction<String, String, Integer>`.
It is not the calculated integer length. You obtain the length by invoking
that new function's `apply` method with both strings.

Start chaining from the first operation in the desired flow. For the
exercise's combiner and length function, the combined string must be
produced before its length can be measured.

The standard `BiFunction` API has `andThen` and does not define a `compose`
method. The absence is an API design fact; do not infer that manually
preprocessing arguments or using a pair object is impossible.

## Nearby contracts

- `Function<T, R>` accepts one argument and returns a result.
- `BiConsumer<T, U>` accepts two arguments and returns `void`.
- `BiPredicate<T, U>` accepts two arguments and returns `boolean`.
- `BinaryOperator<T>` is a `BiFunction<T, T, T>`: two inputs and a result
  all using the same type. It will be useful when reduction is introduced.

## Practise and revise

Exercise: [BiFunctionExercise.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/BiFunctionExercise.java).
Review: [your partial attempt](../../exercise-reviews/functional-interfaces/bifunction.md).

Finish composition after the single-input function chapter is clear. For
the name/age pairing TODO, read [IntStream ranges](../streams/intstream.md#ranges-and-array-sources)
before using indices in its stream pipeline.

Explain each type argument, the result type of `andThen`, and the input
received by the second function. Predict the output before invoking `apply`.

Reference: [BiFunction API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/function/BiFunction.html).

