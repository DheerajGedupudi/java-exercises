# Creating streams: existing values and generated values

[Home](../../README.md) · Previous: [Pipeline overview](overview.md) · Next: [IntStream](intstream.md)

## Sources from values you already have

| Source | Example | Result |
|---|---|---|
| List | `names.stream()` | A stream of the list's element type |
| Object array | `Arrays.stream(fruits)` | A stream of the array's reference element type |
| Individual objects | `Stream.of("red", "blue")` | `Stream<String>` |
| No elements | `Stream.<String>empty()` | An empty `Stream<String>` |
| String's numeric char units | `sentence.chars()` | `IntStream` |

The snippets assume appropriate variables and imports. For a primitive
`int[]`, `Arrays.stream` instead returns an `IntStream`.

`Stream.empty().count()` returns `0` as a `long`. Counting consumes the
stream. Each source call can create a fresh stream for another pipeline.

## Generate and limit

`Stream.generate` accepts a [Supplier](../functional-interfaces/supplier.md).
Each element is supplied when processing requests a value. The generated
source has no natural end.

```java
Stream.generate(() -> "tick")
        .limit(2)
        .forEach(System.out::println);
```

This snippet assumes `Stream` is imported. The supplier takes no arguments
and returns a string. `limit(2)` bounds the pipeline, and terminal `forEach`
consumes and prints the values. An infinite source does not mean allocating
an infinite collection upfront.

The method contracts explain the chain:

| Call | Input | Result |
|---|---|---|
| `Stream.generate(...)` | A supplier | A stream with no built-in end |
| `.limit(2)` | Maximum element count | A bounded stream description |
| `.forEach(...)` | A consumer | `void`; traversal performs the actions |

## A complete example

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreatingStreamsDemo {
    public static void main(String[] args) {
        List.of("oak", "elm").stream().forEach(System.out::println);
        Arrays.stream(new String[]{"red", "blue"}).forEach(System.out::println);
        Stream.generate(() -> "tick").limit(2).forEach(System.out::println);
        System.out.println(Stream.empty().count());
    }
}
```

Output:

```text
oak
elm
red
blue
tick
tick
0
```

The first two sources have two existing elements each. The generated source
could keep supplying `tick`; the limit bounds this traversal to two. Counting
an empty source yields zero.

## Random numbers: separate the arithmetic from the pipeline

`Math.random()` returns a double in `[0.0, 1.0)`. As a separate arithmetic
example, a die value from one through six can be produced with:

```java
int roll = (int) (Math.random() * 6) + 1;
```

Multiplication produces a nonnegative value below six. Casting discards its
fractional part, producing zero through five; adding one shifts the interval.
Multiply before casting. Casting `Math.random()` to `int` first would give
zero before the multiplication.

For the exercise's zero-through-100 interval, use an inclusive interpretation:
there are 101 possible integer results. Decide the multiplier from that
range, put the expression in a supplier, bound the stream to ten, and print
the values. Random results can repeat; distinctness is not part of this task.

If a task specifies an exclusive upper bound, use that different range
explicitly. Stating the boundary matters more than guessing from the word
"between."

For comparison, these arithmetic expressions produce different integer ranges:

| Expression | Possible results |
|---|---|
| `(int) (Math.random() * 10)` | Zero through nine |
| `(int) (Math.random() * 100)` | Zero through 99 |
| `(int) (Math.random() * 101)` | Zero through 100 |

The multiplication scales a value below one; the cast truncates the
nonnegative result. To include an upper integer endpoint starting from zero,
the multiplier is one greater than that endpoint. A few random outputs do
not prove endpoint coverage; reason from the expression's bounds.

## Character sources

`sentence.chars()` supplies numeric Java `char` values as ints. For `Hello`,
they are `72, 101, 108, 108, 111`. The hint's `mapToObj(c -> (char) c)` makes
a `Stream<Character>` before printing. See [IntStream and text](intstream.md#string-chars-and-character-output)
for the cast and the Unicode distinction.

## Practise and revise

Exercise: [CreatingStreams.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/CreatingStreams.java).
Review: [the checked attempt](../../exercise-reviews/streams/creating-streams.md).

Explain which source already has elements and which source supplies new
values on demand. For a generated pipeline, identify the supplier, the
bound, and the terminal action. Predict how many values the example prints.

References: [Stream API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html),
[Math.random](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Math.html#random()).

