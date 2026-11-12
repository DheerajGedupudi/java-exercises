# Consumer: give an input to an action

[Home](../../README.md) · Previous: [Supplier](supplier.md) · Next: [Function](function.md)

## Purpose and contract

Use a `Consumer<T>` when an operation receives a value and performs an action
without returning a result to its caller. Printing, sending a value to a
handler, and updating an existing collection are common examples.

Import `java.util.function.Consumer`. Its abstract method is:

```java
void accept(T value);
```

For `Consumer<String>`, this becomes `void accept(String value)`. The generic
argument describes the input. A `Consumer` can change something or print
something even though its own method returns `void`.

## Syntax

These are alternatives, assuming the import above:

```java
Consumer<String> printer = (String text) -> {
    System.out.println(text);
};
```

```java
Consumer<String> printer = text -> System.out.println(text);
```

```java
Consumer<String> printer = System.out::println;
```

The declaration defines the action. `printer.accept("ready")` invokes it.
There is no returned string to assign from `accept`; the implementation
prints to the console as its action.

## A complete example: chaining actions

```java
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> show = text -> System.out.println("seen:" + text);
        Consumer<String> size = text -> System.out.println("length:" + text.length());
        Consumer<String> both = show.andThen(size);

        System.out.println("defined");
        both.accept("cat");
        List.of("a", "bb").forEach(size);
    }
}
```

Output:

```text
defined
seen:cat
length:3
length:1
length:2
```

`show.andThen(size)` builds another consumer. It does not invoke either
action yet. `both.accept("cat")` runs `show` and then `size`, supplying
the same input string to both.

There is no output value from the first consumer to feed into the second.
Both actions operate on `"cat"`. If the first action throws, the second
action is not performed for that invocation.

## Why pass a Consumer?

A reusable helper can control traversal while the caller supplies the action:

```java
static void deliver(String text, Consumer<String> destination) {
    destination.accept(text);
}
```

The helper's caller can choose printing, storing, or another compatible
action. Your comparator example supplied a sorting decision; this helper
receives an action for a string.

`List.forEach` follows this idea. It supplies each list element to the
consumer. A stream's `forEach` also accepts a consumer and is a terminal
operation. Calling `names.forEach(...)` on a list does not require creating
a stream first.

## Consumer versus Function

Suppose the task is to uppercase a string. A `Function<String, String>` can
return the uppercase text for further use. A `Consumer<String>` can print
the uppercase text, but its `accept` method returns no value.

Ask what the caller needs: a result to continue computing with, or an action
performed on the input? That is the useful distinction.

A consumer body may call a value-returning method and discard that method's
result. Its own functional contract still returns `void`.

## BiConsumer: two inputs, no result

`BiConsumer<T, U>` has the method `void accept(T first, U second)`.
For example, a name and a score can be inputs to an action that updates a map.
Both generic arguments describe inputs; there is no result type argument.

For example, this fragment assumes `Map`, `HashMap`, and `BiConsumer` are imported:

```java
Map<String, Integer> counts = new HashMap<>();
BiConsumer<String, Integer> store = (key, value) -> counts.put(key, value);
store.accept("x", 2);
System.out.println(counts);
```

The lambda captures the map reference. Declaring it does not call `put`;
`accept` performs the insertion. Printing the map is a separate action.
The captured local reference is effectively final, while the map's contents
can still change. `put` returns a value, which this consumer discards.

Read this next to [BiFunction](bifunction.md), which also accepts two inputs
but returns a result.

## Practise and revise

Exercise: [ConsumerExercise.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/ConsumerExercise.java).

Start with the first three TODOs, then predict the output of the combined
consumer before running it. Continue with the remaining TODOs after the
contract and chaining behavior make sense.

Explain what each consumer receives, where the action occurs, and why
`Consumer.andThen` passes the original input to both actions. Compare that
with the result flow in the next chapter's `Function.andThen`.

Reference: [Consumer API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/function/Consumer.html).

