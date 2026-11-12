# Filtering: keep elements that satisfy a condition

[Home](../../README.md) · [Predicate](../functional-interfaces/predicate.md) · [Pipeline overview](overview.md)

## Purpose, input, and result

`filter` selects elements from a stream. Its argument is a predicate: a
condition accepting an element and returning a boolean. A true answer keeps
the element; a false answer discards it from the result pipeline.

For a `Stream<String>`, the predicate tests strings, and the returned stream
is still a `Stream<String>`. The predicate returns a boolean, but `filter`
returns a stream. Those are two different contracts.

It is an intermediate operation: it describes another processing stage.
Element testing happens when a terminal operation consumes this pipeline.

## A complete example

```java
import java.util.List;

public class FilteringDemo {
    public static void main(String[] args) {
        List<String> animals = List.of("ant", "tiger", "fox");
        animals.stream()
                .filter(animal -> animal.length() == 3)
                .forEach(System.out::println);
    }
}
```

Output:

```text
ant
fox
```

Read the calls in order:

| Call | What it contributes |
|---|---|
| `animals.stream()` | A source stream of strings |
| `.filter(...)` | A condition keeping three-character strings |
| `.forEach(...)` | Terminal consumption that prints each retained string |

The lambda returns true for `ant` and `fox`, and false for `tiger`.
Those original strings are the retained values. `filter` does not transform
them into their lengths or booleans, and this pipeline does not remove
elements from the original list.

## Building on Predicate

A named `Predicate<String>` can be passed directly to `filter`. A lambda
or a compatible method reference can supply the same kind of condition.
You do not need to call `test` manually for every element; stream processing
invokes the predicate as values reach that stage.

Chaining two filters keeps elements satisfying both conditions, evaluated
in pipeline order. An element rejected by the first does not reach the second.
Choose the order deliberately when an earlier guard is needed.

## Match the condition to the wording

`filter` applies the condition you supply. Choose the string operation that
represents the requested test:

| Condition | Meaning |
|---|---|
| `text.startsWith("a")` | The text begins with `a` |
| `text.contains("a")` | The text has `a` anywhere |

For `"train"`, the containment check is true but the prefix check is false.
That is a useful input for detecting a mistaken choice. A chain of filters
can be syntactically correct while its selected condition answers the wrong
question. Predict matching values before running the pipeline.

## Nulls and method references

The exercise includes a list containing nulls. `Objects::nonNull` is a
method reference to a boolean test and can supply the filtering predicate.
It requires `java.util.Objects` to be imported. A lambda checking against
null is another form of the same condition.

Remove nulls before a later stage calls an instance method such as `length`
on the element. The exercise already supplies an appropriate list; `List.of`
does not accept null elements when constructing a list.

## Print elements or collect a list

Choose the terminal operation from the required result:

- `forEach` performs an action for each retained element and returns `void`.
- `collect(Collectors.toList())` returns a list of retained elements. Import
  `java.util.stream.Collectors` and print that list separately when requested.

`Stream.toList()` is another terminal operation that returns a list. See
[its result contract](overview.md#collecting-into-a-list). When a TODO explicitly
asks for `Collectors.toList()`, use the collector-based form to practise that
API even if the other method would produce the same values.

Without consumption, a pipeline containing only intermediate operations
has not evaluated the conditions for its elements. Review
[execution timing](overview.md#intermediate-versus-terminal) if this is unclear.

## Practise and revise

Exercise: [FilterBasics.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/FilterBasics.java).
Review: [the submitted conditions and outputs](../../exercise-reviews/streams/filter-basics.md).
Attempt its six TODOs. Predict which elements remain before running each
pipeline. Identify the predicate and terminal operation, and distinguish
printing individual values from printing one collected list.

Explain these before moving on:

1. What does the predicate return, and what does `filter` return?
2. What starts execution of the example?
3. Does a rejected element reach the next filter?
4. How does filtering differ from mapping an element to its length?

Reference: [Stream.filter API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html#filter(java.util.function.Predicate)).

