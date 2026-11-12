# Mapping: transform each element

[Home](../../README.md) · [Function](../functional-interfaces/function.md) · [Filtering](filtering.md)

## Purpose, input, and result

`map` applies a transformation to each processed element. Its argument is
a function: it accepts one input element and returns the value to put in
the resulting stream.

For a `Stream<T>`, a compatible `Function<T, R>` produces a `Stream<R>`.
The input and result types can be the same or different. For example,
strings can become uppercase strings or integer lengths.

It is an intermediate operation. It returns another stream describing
work; a terminal operation drives processing and obtains or uses results.

## A complete example

```java
import java.util.List;
import java.util.stream.Stream;

public class MappingDemo {
    public static void main(String[] args) {
        List<String> words = List.of("sun", "moon");
        Stream<Integer> lengths = words.stream().map(String::length);
        lengths.forEach(System.out::println);
    }
}
```

Output:

```text
3
4
```

The stages have different roles:

| Stage | Input and result |
|---|---|
| `words.stream()` | Source elements are strings |
| `.map(String::length)` | Each string becomes its length; result is `Stream<Integer>` |
| `.forEach(...)` | Terminal action prints each integer result |

`String::length` expresses `word -> word.length()`. The primitive method
result is boxed into `Integer` for the object stream. The original strings
in `words` are not replaced by this pipeline.

## Compare mapping and filtering

| Operation | Supplied behavior | What reaches the next stage |
|---|---|---|
| `filter(condition)` | A boolean test | Original elements for which the test is true |
| `map(transformation)` | A value-producing function | The returned value for each processed input |

For example, mapping numbers with a boolean expression produces boolean
results. It does not select just the numbers answering true. Use filtering
when the goal is selection, and mapping when the goal is transformation.

The mapper must return the transformed value. Printing alone returns no
value, so a printing action belongs in `forEach`, rather than serving as
the result-producing body of `map`.

## Chaining operations

Filtering before mapping tests the original value; filtering after mapping
tests the transformed value. Those orders can express different questions.
Trace the actual values and types between stages.

Two mapping stages can pass a value through two transformations, much like
`Function.andThen`. Each mapper receives the previous stage's result, not
the original value from before all mappings.

## Choose the terminal operation

Use `forEach` to perform an action on each transformed value. To keep the
results in a list, use an appropriate terminal list operation and print
the returned list separately when requested. See
[list accumulation](overview.md#collecting-into-a-list).

`map` on an object stream creates another object stream. `mapToInt` instead
creates an `IntStream`, which is useful for numeric operations. See the
[primitive mapping table](intstream.md#know-which-kind-of-stream-a-mapping-returns)
when that distinction is needed.

## Practise and revise

Exercise: [MapBasics.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/MapBasics.java).
Review: [all six completed TODOs](../../exercise-reviews/streams/map-basics.md).
Attempt its six TODOs. Predict output and identify the input and result
types for every mapping. Pay attention to the distinction between an entire
uppercase name and a name with only its first letter capitalized.

Explain these before moving on:

1. Why does the example produce `Stream<Integer>` from `Stream<String>`?
2. What is the difference between returning a value and printing it?
3. Which values does a filter inspect when it comes after a mapping?
4. Can mapping change values while keeping the same element type?

Reference: [Stream.map API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html#map(java.util.function.Function)).

