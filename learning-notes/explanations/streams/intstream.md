# IntStream: primitive integer processing

[Home](../../README.md) · Previous: [Creating streams](creating-streams.md) · [Quick reference](../../revision/quick-reference.md)

## What it is

`IntStream` is the standard library stream interface for primitive `int`
elements. It belongs to `java.util.stream`.

| Type | Elements processed |
|---|---|
| `Stream<String>` | `String` objects |
| `Stream<Integer>` | `Integer` objects |
| `IntStream` | Primitive `int` values |

It supports numeric work without boxing each element into an `Integer`.
It is still a processing pipeline, rather than a collection storing values.
`LongStream` and `DoubleStream` provide the corresponding primitive forms.

## Ranges and array sources

`IntStream.range(start, end)` includes the start and excludes the end, like
`for (int i = start; i < end; i++)`. `rangeClosed` includes both endpoints.

| Source expression | Values supplied |
|---|---|
| `IntStream.range(2, 5)` | `2, 3, 4` |
| `IntStream.rangeClosed(2, 5)` | `2, 3, 4, 5` |
| `IntStream.of(2, 5, 9)` | `2, 5, 9` |
| `Arrays.stream(new int[]{2, 5, 9})` | `2, 5, 9` |

This is also useful when an operation needs indices to access corresponding
elements in two lists. The range supplies the indices; the mapping body
chooses what to compute from each index.

## A complete example

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStreamDemo {
    public static void main(String[] args) {
        System.out.println(IntStream.range(1, 5).sum());
        System.out.println(Arrays.stream(new int[]{3, 6, 9}).sum());
        System.out.println(List.of("hi", "java").stream().mapToInt(String::length).sum());

        List<Integer> values = IntStream.rangeClosed(1, 3)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(values);

        "Hi".chars().mapToObj(code -> (char) code).forEach(System.out::println);
    }
}
```

Output:

```text
10
18
6
[1, 2, 3]
H
i
```

The first two lines sum primitive sources. The third converts object-stream
elements to primitive lengths before summing. `boxed` then demonstrates the
opposite direction, converting primitive values to `Integer` objects for
an object stream and list collection.

## Know which kind of stream a mapping returns

| Call | Result |
|---|---|
| `Stream<T>.map(function)` | An object stream, `Stream<R>` |
| `Stream<T>.mapToInt(function)` | `IntStream` |
| `IntStream.map(operation)` | Another `IntStream` |
| `IntStream.mapToObj(function)` | An object stream, `Stream<R>` |
| `IntStream.boxed()` | `Stream<Integer>` |

An `IntStream` uses primitive functional contracts, such as `IntPredicate`
for `filter` and `IntConsumer` for `forEach`. Their parameters are primitive
ints. Lambdas often look the same because Java infers those parameter types.
A `Predicate<Integer>` object is a different interface from `IntPredicate`.

## String chars and character output

`String.chars()` returns the numeric values of its Java `char` units as ints.
For `Hello`, the values are `72, 101, 108, 108, 111`.

```java
System.out.println(72);        // 72
System.out.println((char) 72); // H
```

Printing an int prints a number. A cast changes how the value is interpreted
for character output. In `mapToObj(code -> (char) code)`, the cast produces a
`char`, which is boxed into `Character` for the object stream.

Using `IntStream.map` for the cast would keep an integer stream: its mapping
result is converted to `int`. `mapToObj` is what changes the stream kind.

More precisely, Java strings use UTF-16 code units. A supplementary Unicode
character can occupy two `char` units. `codePoints()` combines valid surrogate
pairs and also returns an `IntStream`. A code point beyond the `char` range
cannot be represented by a simple cast to one `char`. Revisit that distinction
when processing text beyond the current simple examples.

## Numeric terminal operations

| Operation | Result type |
|---|---|
| `sum()` | `int` |
| `count()` | `long` |
| `average()` | `OptionalDouble` |
| `min()` / `max()` | `OptionalInt` |

The optional results account for empty inputs. For example,
`IntStream.empty().average().orElse(0.0)` explicitly chooses zero as the
fallback for that calculation. Learn `Optional` handling before relying
on these methods for inputs that might be empty.

### OptionalInt and OptionalDouble

These are separate classes for optional primitive results:

| Returned value | What it represents | Value extraction |
|---|---|---|
| `OptionalInt` | An int or no result | `orElse(intFallback)` or `getAsInt()` |
| `OptionalDouble` | A double or no result | `orElse(doubleFallback)` or `getAsDouble()` |

They express absence like [Optional](../foundations/optional.md), but they
are not `Optional<Integer>` and `Optional<Double>`. The `getAs...` methods
throw `NoSuchElementException` if empty. A fallback makes your empty-case
decision explicit; it is not an identity for the numeric operation.

`sum()` returns zero on an empty IntStream. An average, minimum, or maximum
has no value to return for empty input, so those terminals use optional
results instead. An average can contain a fractional result even though
its input elements are ints.

```java
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class IntStreamNumericResultsDemo {
    public static void main(String[] args) {
        OptionalDouble average = IntStream.of(4, 8, 12).average();
        System.out.println(average.orElse(0.0));

        OptionalInt smallest = IntStream.of(9, 2, 6).min();
        System.out.println(smallest.orElse(0));

        OptionalInt largest = IntStream.of(9, 2, 6).max();
        System.out.println(largest.orElse(0));

        System.out.println(IntStream.empty().average().isPresent());
    }
}
```

Output:

```text
8.0
2
9
false
```

`of` receives the listed ints and returns an IntStream. `average`, `min`,
and `max` each consume that stream and return the optional type shown
above. The example creates a fresh stream for each calculation. Its zero
fallbacks are illustrative choices for absent results.

Terminal operations consume the stream. Create a fresh stream for another
traversal, or use an appropriate summary operation when learning statistics.

### Same values, separate streams

Calling `IntStream.of` twice with the same values creates two separate
streams. Each is available for its own terminal operation. Repeating the
source values does not reopen or reuse a consumed stream.

For example, these fragments run inside a method with IntStream imported:

```java
int minimum = IntStream.of(9, 2, 6).min().orElse(0);
int maximum = IntStream.of(9, 2, 6).max().orElse(0);
```

The results are two and nine. Follow the separate lifetimes:

```text
first IntStream.of(9, 2, 6)  → stream A → min() → OptionalInt containing 2
second IntStream.of(9, 2, 6) → stream B → max() → OptionalInt containing 9
```

Compare that with an attempted reuse:

```java
IntStream stream = IntStream.of(9, 2, 6);
int minimum = stream.min().orElse(0);
// Calling stream.max() here would reuse the consumed stream.
```

A stream variable holds one stream instance; it is not a recipe that
automatically constructs a fresh stream whenever you invoke a terminal.
The original input values are still available to create another pipeline.

### Sum and fallback syntax: valid versus more direct

On an IntStream, `reduce(0, (a, b) -> a + b)` correctly computes a sum.
`sum()` directly expresses that operation and returns an int. Use `reduce`
when an exercise requires it or when practising the combining rule; use
the named numeric terminal when it communicates the task more clearly.

`average().orElse(0)` is valid: Java widens the int literal zero to double
for the fallback argument. Writing `orElse(0.0)` makes the double type
visible to the reader. This is a readability choice, not a correctness fix.

## LongStream and DoubleStream sources

`LongStream` processes primitive long values. Its `range(start, end)`
includes the start and excludes the end; `rangeClosed(start, end)` includes
both. Both factories return a LongStream, and its terminal `sum()` returns
a long. A total can exceed int capacity even when each individual source
value fits in an int, as in a sum up to one million.

`DoubleStream.generate` accepts a DoubleSupplier: a function taking no
arguments and returning a primitive double. It returns an unbounded source.
The supplier chooses each value; `limit` bounds how many are requested by
the terminal pipeline. This is the primitive-double form of the
[Supplier](../functional-interfaces/supplier.md) idea.

```java
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

public class PrimitiveStreamSourcesDemo {
    public static void main(String[] args) {
        System.out.println(LongStream.range(1, 4).sum());
        System.out.println(LongStream.rangeClosed(1, 4).sum());

        DoubleStream.generate(() -> 0.125)
                .limit(2)
                .forEach(value -> System.out.printf("%.4f%n", value));
    }
}
```

Output with a decimal-point locale:

```text
6
10
0.1250
0.1250
```

The constant supplier makes this example's output reproducible. In the
exercise, `Math::random` instead supplies values from zero inclusive to one
exclusive. There is no requirement that the sampled values be distinct.
`%.4f` formats a floating-point value with four digits after the decimal
separator, and `%n` adds a platform newline. Formatting rounds the display;
a value just below one can therefore print as `1.0000`.

For the exercise's endpoint, distinguish the two sums explicitly:

| Source | Values | Sum |
|---|---|---|
| `LongStream.range(1, 1_000_000)` | One through 999,999 | `499999500000` |
| `LongStream.range(1, 1_000_001)` | One through 1,000,000 | `500000500000` |
| `LongStream.rangeClosed(1, 1_000_000)` | One through 1,000,000 | `500000500000` |

The exercise names `range` but also says one to one million. That wording
is ambiguous about including the final number. The first expression uses
the named API correctly. To include one million while retaining `range`,
use the second expression; `rangeClosed` also states the inclusive intent.

## Practise and revise

Current exercise: [IntStreamBasics.java](../../../src/main/java/com/amigoscode/_7_streams/_2_intstreams/IntStreamBasics.java).
Attempt TODOs 1–6 after the refresher above:

- TODOs 1–4 use range sources, printing, sum, and boxing into an object
  stream for list collection.
- TODOs 5–6 use the numeric optional results. For min and max, create fresh
  streams as the task suggests; a summary-statistics alternative can wait.

[CreatingStreams.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/CreatingStreams.java)
is the earlier exercise that introduced these primitive sources.

Explain `range` versus `rangeClosed`, each conversion in the example, and
why `chars()` initially supplies numbers. Predict the type after each call
as well as the final output. Explain why sum returns an int while average,
min, and max return optional results.

References: [IntStream API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/IntStream.html),
[String.chars](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/String.html#chars()).

