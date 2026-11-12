# Generating sequences with iterate

[Home](../../README.md) · [IntStream basics](intstream.md) · [Creating streams](creating-streams.md)

## Purpose and contracts

`IntStream.iterate` creates a stream by starting with a seed and repeatedly
applying a rule to the previous value. Each element is a primitive int.
Both forms below return an IntStream, so terminal operations are still
needed to process the generated values.

| Form | Inputs | How it ends |
|---|---|---|
| `iterate(seed, next)` | Initial int and a rule from int to int | Unbounded source; use a suitable stopping operation such as `limit` |
| `iterate(seed, hasNext, next)` | Initial int, an int-to-boolean test, and the next-value rule | Stops at the first candidate that fails the test |

The next-value rule implements `IntUnaryOperator`: one int goes in and one
int comes back. The test implements `IntPredicate`: one int goes in and a
boolean comes back. These interfaces are in `java.util.function`; when
passing lambdas directly, Java infers the required interface from the method.
They are primitive relatives of the [Function](../functional-interfaces/function.md)
and [Predicate](../functional-interfaces/predicate.md) contracts.

## Two arguments: seed and next-value rule

This fragment assumes IntStream is imported and runs inside a method:

```java
IntStream.iterate(3, n -> n + 3)
        .limit(4)
        .forEach(System.out::println);
```

Read it as:

1. Start with the seed, three. That is the first element.
2. Apply the next-value rule to get six, then nine, then twelve, and so on.
3. Limit the pipeline to four elements.
4. The terminal `forEach` drives traversal and prints each element.

The values printed are `3`, `6`, `9`, and `12`, each on its own line.
`limit(4)` specifies an element count, not a maximum numeric value.
The source is generated on demand; Java does not allocate an infinite list.

In this form, the seed is emitted before the next-value rule is applied.
The rule receives the preceding value, so `n -> n + 3` advances by three
on each step.

## Three arguments: add a continuation test

```java
IntStream.iterate(3, n -> n <= 12, n -> n + 3)
        .forEach(System.out::println);
```

The first lambda tests whether the current candidate should be emitted.
The second computes the next candidate. The test includes the seed:

```text
candidate 3:  test passes → emit 3  → next candidate 6
candidate 6:  test passes → emit 6  → next candidate 9
candidate 9:  test passes → emit 9  → next candidate 12
candidate 12: test passes → emit 12 → next candidate 15
candidate 15: test fails  → stop; do not emit 15
```

For this sequential example, the flow matches:

```java
for (int n = 3; n <= 12; n = n + 3) {
    System.out.println(n);
}
```

There is no fixed numeric endpoint built into `iterate`. The supplied test
determines the stopping rule. If the seed fails the test, the stream is
empty. If the test never fails, the source can still be unbounded.

## Complete example

```java
import java.util.stream.IntStream;

public class IntStreamIterationDemo {
    public static void main(String[] args) {
        System.out.println("Two arguments:");
        IntStream.iterate(3, n -> n + 3)
                .limit(4)
                .forEach(System.out::println);

        System.out.println("Three arguments:");
        IntStream.iterate(3, n -> n <= 12, n -> n + 3)
                .forEach(System.out::println);
    }
}
```

Output:

```text
Two arguments:
3
6
9
12
Three arguments:
3
6
9
12
```

Each factory call creates a fresh stream. The two examples use different
stopping mechanisms to generate the same four values.

## Object-state iteration: carrying a Fibonacci pair

`Stream.iterate(seed, next)` is the object-stream version. Its seed is an
object of type T, and its next-value function maps T to another T. It returns
`Stream<T>`. For the Fibonacci exercise, T is `int[]`: one stream element
is a whole two-number array.

The state `[a, b]` means the current Fibonacci number and the next one. The
rule constructs the next state `[b, a + b]`:

```text
state [0, 1] → output 0
state [1, 1] → output 1
state [1, 2] → output 1
state [2, 3] → output 2
state [3, 5] → output 3
```

`mapToInt(pair -> pair[0])` projects each array onto its first int. The
pipeline changes from `Stream<int[]>` to IntStream, with one output value
for each input state. The array stores the two pieces of state needed by
the recurrence; the final output contains only the first component.

## Moving limit across this mapToInt

Both placements in the exercise produce the same first ten Fibonacci
numbers:

```text
states → limit(10) → mapToInt(first component) → output
states → mapToInt(first component) → limit(10) → output
```

The projection is one-to-one and preserves order: every state supplies
exactly one int. Selecting the first ten states and then projecting them
gives the same values as projecting and selecting the first ten ints.
Both pipelines are lazy. Placing the limit afterward does not require
mapping an infinite sequence before the terminal can finish.

### Complete comparison

```java
import java.util.List;
import java.util.stream.Stream;

public class FibonacciLimitPlacementDemo {
    private static Stream<int[]> fibonacciStates() {
        return Stream.iterate(new int[]{0, 1},
                pair -> new int[]{pair[1], pair[0] + pair[1]});
    }

    public static void main(String[] args) {
        List<Integer> limitFirst = fibonacciStates()
                .limit(10)
                .mapToInt(pair -> pair[0])
                .boxed()
                .toList();

        List<Integer> mapFirst = fibonacciStates()
                .mapToInt(pair -> pair[0])
                .limit(10)
                .boxed()
                .toList();

        System.out.println(limitFirst);
        System.out.println(mapFirst);
        System.out.println(limitFirst.equals(mapFirst));
    }
}
```

Output:

```text
[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
true
```

The helper is a factory: each call returns a fresh stream. Both versions
are acceptable, including the exercise's existing limit-first version.

### This does not mean all pipeline stages can be reordered

With `java.util.stream.IntStream` imported, compare these expressions
inside a method:

```java
IntStream.rangeClosed(1, 6)
        .limit(3)
        .filter(n -> n % 2 == 0)
        .boxed().toList(); // [2]

IntStream.rangeClosed(1, 6)
        .filter(n -> n % 2 == 0)
        .limit(3)
        .boxed().toList(); // [2, 4, 6]
```

The first considers three original values and keeps the even ones among
them. The second keeps even values first, then takes up to three of those.
Filtering can discard elements, so its position changes what limit counts.
See [filtering](filtering.md) and [mapping](mapping.md).

## Current exercise and revision

Attempt TODOs 1–2 in
[IntStreamIterate.java](../../../src/main/java/com/amigoscode/_7_streams/_2_intstreams/IntStreamIterate.java).
For each TODO, identify the seed, the next-value rule, and the stopping
mechanism before writing the pipeline. Predict the output and then run it.
The object-state section above prepares TODO 3. The
[primitive-stream source refresher](intstream.md#longstream-and-doublestream-sources)
covers the APIs used by TODOs 4–5. Review the endpoint wording in TODO 4:
`range` excludes its upper bound.

Self-check:

1. What does `iterate(2, n -> n * 2).limit(4)` supply?
2. What happens with `iterate(5, n -> n < 5, n -> n + 1)`?
3. Does `limit(4)` mean four elements or values no greater than four?

<details>
<summary>Answers</summary>

1. Two, four, eight, and sixteen. The seed is the first element.
2. It is empty: the seed fails the continuation test.
3. Four elements. Their numeric values can be much larger than four.

</details>

References: [IntStream.iterate with two arguments](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/IntStream.html#iterate(int,java.util.function.IntUnaryOperator)),
[IntStream.iterate with three arguments](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/IntStream.html#iterate(int,java.util.function.IntPredicate,java.util.function.IntUnaryOperator)).
Related: [Stream.iterate](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html#iterate(T,java.util.function.UnaryOperator)),
[Stream.mapToInt](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html#mapToInt(java.util.function.ToIntFunction)).
