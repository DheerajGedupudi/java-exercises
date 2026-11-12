# Function: transform an input into a result

[Home](../../README.md) · Previous: [Consumer](consumer.md) · Next: [BiFunction](bifunction.md)

## Purpose and contract

Use a `Function<T, R>` when one value goes in and a result comes back. The
types can differ: a string can become its integer length, for example.

Import `java.util.function.Function`. Its abstract method is:

```java
R apply(T input);
```

`T` describes the input and `R` describes the result. For
`Function<String, Integer>`, the method becomes `Integer apply(String input)`.
The two generic arguments do not mean two method parameters.

## Syntax and invocation

These are alternative declarations:

```java
Function<String, Integer> size = (String text) -> {
    return text.length();
};
```

```java
Function<String, Integer> size = text -> text.length();
```

```java
Function<String, Integer> size = String::length;
```

Calling `size.apply("java")` returns `4` as an `Integer`. The method's
primitive `int` result is boxed to match that result type. Generic type
arguments use reference types, so write `Integer` rather than `int` there.

The definition holds behavior; `apply` runs it. Assign or print the result
when the caller needs it. An unused return value is discarded.

## A complete example: andThen and compose

```java
import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        Function<String, String> strip = String::strip;
        Function<String, Integer> size = String::length;
        Function<Integer, String> label = value -> "size=" + value;

        Function<String, Integer> cleanSize = strip.andThen(size);
        Function<String, Integer> sameOrder = size.compose(strip);
        Function<String, String> report = cleanSize.andThen(label);

        System.out.println(cleanSize.apply("  java  "));
        System.out.println(sameOrder.apply("  java  "));
        System.out.println(report.apply("  java  "));
    }
}
```

Output:

```text
4
4
size=4
```

`String.strip()` removes leading and trailing whitespace. The input above
therefore becomes `"java"` before its length is measured.

## Follow the values, not just the method names

For `strip.andThen(size)`, the flow is:

```text
"  java  " → strip → "java" → size → 4
```

`first.andThen(second)` runs the first function, then gives its result to
the second. `first.compose(before)` runs the argument function first, then
gives that result to the receiver function.

Consequently, `strip.andThen(size)` and `size.compose(strip)` express the
same order here. Building either composed function does not run it yet.
Calling `apply` does.

The types must connect. `strip` produces a `String`, and `size` accepts a
`String`, so that direction works. `size.andThen(strip)` does not type-check:
`size` returns an `Integer`, while `strip` expects a `String`.

`Consumer.andThen` runs actions on the same input. `Function.andThen`
passes a result forward to the next function. They share a method name,
but their contracts determine the data flow.

## Why pass a Function?

A helper can accept the transformation separately from the input:

```java
static <T, R> R transform(T value, Function<T, R> operation) {
    return operation.apply(value);
}
```

This is an illustrative generic helper. Its type parameters describe the
input and result types for a particular call. The caller chooses the behavior;
the helper decides where it is invoked.

A stream's `map` uses the same idea for each element. Read the
[pipeline introduction](../streams/overview.md) before the stream TODO in
the exercise, rather than copying the pipeline without understanding it.

## Alternatives for a fixed number-to-word mapping

A chain of `if/else` statements is valid. For a small fixed mapping, a
switch expression makes the alternatives easier to scan:

```java
Function<Integer, String> intToWord = number -> switch (number) {
    case 1 -> "one";
    case 2 -> "two";
    case 3 -> "three";
    case 4 -> "four";
    case 5 -> "five";
    default -> "unknown";
};
```

The first arrow introduces the lambda. The arrows inside the switch choose
the result for each case. The switch itself is an expression supplying the
lambda's result, so this expression body does not need a `return` keyword.
The final semicolon ends the variable declaration.

A lookup table is another option. This alternative assumes both `Map` and
`Function` are imported:

```java
Map<Integer, String> numberWords = Map.of(
        1, "one", 2, "two", 3, "three", 4, "four", 5, "five");
Function<Integer, String> intToWord =
        number -> numberWords.getOrDefault(number, "unknown");
```

`getOrDefault` returns the stored word when the key exists and the supplied
fallback otherwise. `numberWords` is a different variable from the exercise's
later list named `words`. The two snippets are alternatives, not declarations
to paste together into the same scope.

The switch is a straightforward choice for these five cases. A lookup table
is useful when the mapping is better represented as data. Both alternatives
and the original `if/else` implementation were checked for negative, zero,
one-through-five, and out-of-range integer inputs.

## Practise and revise

Exercise: [FunctionExercise.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/FunctionExercise.java).
Review: [your attempt and the mapping alternatives](../../exercise-reviews/functional-interfaces/function.md).

Focus first on TODOs 1–4. Draw the input and result types between the functions
for both `andThen` and `compose`. Then continue with the remaining TODOs.

Explain why a transformation must return the computed value, what the third
stage of `report` receives, and why the reversed composition above fails.

Reference: [Function API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/function/Function.html).

