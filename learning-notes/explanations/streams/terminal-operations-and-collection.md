# Terminal operations: actions and collection results

[Home](../../README.md) · [Mapping](mapping.md) · [Consumer](../functional-interfaces/consumer.md)

## Choose the result you need

`forEach` consumes a stream and performs an action for each element. It
receives a consumer and returns `void`. Printing values is one such action.

This form of `collect` instead receives a collector describing how to
accumulate the elements, and returns the accumulated result. `Collectors`
provides factory methods for common collection recipes.

| Goal | Terminal expression |
|---|---|
| Perform an action per element | `.forEach(action)` |
| Accumulate a list | `.collect(Collectors.toList())` |
| Accumulate a set of unique values | `.collect(Collectors.toSet())` |
| Obtain an unmodifiable list | `.toList()` or `.collect(Collectors.toUnmodifiableList())` |
| Choose a concrete collection type | `.collect(Collectors.toCollection(LinkedList::new))` |

These are pipeline fragments. Import the collection classes and
`java.util.stream.Collectors` as required.

## A complete example

```java
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TerminalOperationsDemo {
    public static void main(String[] args) {
        List<String> values = List.of("red", "blue", "red");
        values.stream().forEach(System.out::println);

        List<String> list = values.stream().collect(Collectors.toList());
        Set<String> unique = values.stream().collect(Collectors.toSet());
        List<String> readOnly = values.stream().collect(Collectors.toUnmodifiableList());
        LinkedList<String> linked = values.stream()
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println(list);
        System.out.println(unique.size());
        System.out.println(unique.contains("red") && unique.contains("blue"));
        System.out.println(readOnly);
        System.out.println(linked.getClass().getSimpleName());
    }
}
```

Output:

```text
red
blue
red
[red, blue, red]
2
true
[red, blue, red]
LinkedList
```

The list retains repeated values. The set contains the two unique values.
The example checks its size and contents without assuming an iteration order.
The final line verifies the concrete class selected by the collection factory.
Each terminal operation uses a fresh stream from the same source list.

## A collector factory is different from the collected result

`Collectors.toList()` creates a collector: a recipe for accumulation.
The surrounding `stream.collect(...)` executes the traversal and returns
the list. By comparison, `stream.toList()` directly performs a terminal
list operation. The receiver and return type tell you which method is being used.

The particular `collect` overload here accepts a `Collector`; you do not
need to implement a custom collector for these exercises.

## Set contents and ordering

Collecting to a set removes duplicate values according to the set's equality
rules. `Collectors.toSet()` does not promise an iteration order or a specific
set implementation. When reviewing output, check the unique elements rather
than requiring the same printed order as the source list.

## Unmodifiable lists and explicit collection types

`Stream.toList()` and `Collectors.toUnmodifiableList()` produce unmodifiable
lists. List mutator methods, such as adding an element, are not supported.
This does not automatically make mutable objects inside a list immutable.
`toUnmodifiableList()` also rejects null elements.

`Collectors.toList()` does not promise a concrete list class or mutability.
Use `toCollection` when a particular collection implementation matters.
For example, `LinkedList::new` supplies empty linked lists to that collector.
The collector uses the factory while accumulating; the reference itself is
not an already populated collection.

## Practise and revise

Exercise: [ForEachAndCollect.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/ForEachAndCollect.java).
Attempt all six TODOs. Choose each terminal operation from the requested
result, then predict the elements, duplicate handling, and collection type.

Explain the difference between returning `void`, returning a collector
recipe, and returning the resulting collection. For the final TODO, connect
the constructor reference back to the [Supplier contract](../functional-interfaces/supplier.md).

References: [Collectors API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Collectors.html),
[Stream API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html).

