# Stream pipelines: source, operations, and execution

[Home](../../README.md) · Previous: [Predicate](../functional-interfaces/predicate.md) · Next: [Creating streams](creating-streams.md)

## What a stream represents

A collection holds elements. A stream describes processing of elements
from a source. The source might be a collection, an array, or a generator
that supplies values on demand.

You already know the loop-based operations: keep matching items, transform
them, and accumulate results. A stream pipeline expresses those operations
as connected method calls.

```text
source → intermediate operations → terminal operation
```

Each call has its own contract. The dots are normal method-call syntax:
the returned stream from one operation becomes the receiver of the next call.

## The interfaces you practised appear as parameters

| Method | Supplied argument | Role |
|---|---|---|
| `filter(predicate)` | `Predicate<T>` | Keep elements answering true |
| `map(function)` | `Function<T, R>` | Transform each retained element |
| `forEach(action)` | `Consumer<T>` | Perform an action as a terminal operation |
| `Stream.generate(supplier)` | `Supplier<T>` | Supply source values on demand |
| `collect(Collectors.toList())` | A collector produced by a factory method | Accumulate into a list |

The collector is another API object; this particular `collect` overload
receives a `Collector`, not one of the four functional interfaces above.
Learn each method's input and result rather than guessing from the dots.

## A complete example

```java
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPipelineDemo {
    public static void main(String[] args) {
        List<String> words = List.of("pen", "notebook", "cup");
        Stream<String> pipeline = words.stream()
                .filter(word -> {
                    System.out.println("checking " + word);
                    return word.length() > 3;
                })
                .map(String::toUpperCase);

        System.out.println("pipeline defined");
        List<String> result = pipeline.collect(Collectors.toList());
        System.out.println(result);
        System.out.println(words);
    }
}
```

Output:

```text
pipeline defined
checking pen
checking notebook
checking cup
[NOTEBOOK]
[pen, notebook, cup]
```

The diagnostic printing inside the filter is for observing this example.
Ordinary transformation pipelines are easier to reason about when their
conditions and mappings avoid changing external state.

## Trace the work

1. `words.stream()` creates the source stream.
2. `filter` and `map` describe intermediate work; they have not processed
   the words when `pipeline defined` prints.
3. `collect` is terminal. It drives traversal and produces the list.
4. `pen` fails the condition. `notebook` passes and becomes `NOTEBOOK`.
   `cup` fails. The result contains one value.
5. This pipeline leaves the original list's values intact.

For this simple filter/map traversal, operations can be applied per element;
separate intermediate lists are not required for each stage. Stateful
operations such as sorting may need to see all input before producing results.

## Intermediate versus terminal

Intermediate operations return streams describing more work. Examples are
`filter`, `map`, and `limit`. Terminal operations finish a traversal or
produce an observable result, such as `collect`, `forEach`, `sum`, or `count`.

Laziness means intermediate element-processing work is deferred until
consumption. It does not mean every statement used to construct a pipeline
is deferred: evaluating method arguments is still ordinary Java evaluation.

A short-circuiting operation can bound the work. For example, a suitable
`limit` can make processing a generated source finite. Finding a first match
can stop without examining all input. Cover these with concrete examples
before attempting their exercises.

## Collecting into a list

The example above uses `collect(Collectors.toList())`. The exercise's
`stream.map(function).toList()` form is also valid for this project.
`Stream.toList()` is a terminal operation and returns an unmodifiable list.
Reading and printing that list are fine; changing its elements or structure
through list mutator methods is not supported.

This result property is part of the method's contract. See the
[Stream.toList API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html#toList()).

## A stream is consumed once

After a terminal operation, obtain a fresh stream from the source for another
traversal. Reusing a consumed stream is invalid and can throw
`IllegalStateException`. Keeping the source list is different from keeping
one stream instance.

The following is an illustrative invalid sequence, assuming `words` exists:

```java
Stream<String> once = words.stream();
once.count();
once.forEach(System.out::println); // invalid: already consumed
```

Calling `words.stream()` again creates a fresh stream.

## Refresher before each new operation

Identify four things before chaining an unfamiliar method: what it does,
what argument it accepts, what it returns, and whether it is intermediate
or terminal. Then run a small example before solving the exercise.

Exercises: [FilterBasics.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/FilterBasics.java),
[MapBasics.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/MapBasics.java),
[ForEachAndCollect.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/ForEachAndCollect.java),
and later [LazyStreams.java](../../../src/main/java/com/amigoscode/_7_streams/_11_understanding/LazyStreams.java).

Explain which operation starts the example's traversal, the type after each
stage, and the difference between printing elements and collecting them.

Reference: [Stream package overview](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/package-summary.html).

