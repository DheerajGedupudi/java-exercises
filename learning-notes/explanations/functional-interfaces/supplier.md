# Supplier: provide a value when asked

[Home](../../README.md) · [Study order](../../learning-plan.md) · Next: [Consumer](consumer.md)

## Purpose and contract

Use a `Supplier<T>` when the caller needs a way to obtain a value later,
without passing an argument to that operation. It can represent a factory,
a deferred calculation, or a way to read a value at the time it is needed.

Import `java.util.function.Supplier`. Its abstract method is:

```java
T get();
```

`T` is the result type. For `Supplier<String>`, the method contract becomes
`String get()`. There are zero method parameters; the generic type argument
describes the result, not an input.

## Syntax, expanded and shortened

These are alternative implementations:

```java
Supplier<String> status = () -> {
    return "ready";
};
```

```java
Supplier<String> status = () -> "ready";
```

The empty `()` means no parameters. The expression supplies the result of
`get`. Calling `status.get()` obtains it. Merely declaring `status` does not
execute its body, and calling `get` does not automatically print the result.

The same contract could be implemented with an anonymous class:

```java
Supplier<String> status = new Supplier<String>() {
    @Override
    public String get() {
        return "ready";
    }
};
```

## A complete example: definition, execution, and a factory

```java
import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<String> status = () -> {
            System.out.println("supplier body");
            return "ready";
        };

        System.out.println("defined");
        String value = status.get();
        System.out.println(value);
        System.out.println(status.get());

        Supplier<StringBuilder> builders = StringBuilder::new;
        StringBuilder first = builders.get();
        StringBuilder second = builders.get();
        first.append("A");
        System.out.println(first == second);
        System.out.println(second.length());
    }
}
```

Output:

```text
defined
supplier body
ready
supplier body
ready
false
0
```

Execution walkthrough:

1. Assigning `status` supplies behavior; its body has not run.
2. `defined` prints before the first `get` call.
3. `get` prints its diagnostic and returns `"ready"`; the caller then prints it.
4. The second `get` executes the body again, even though it returns the same text.
5. Each invocation of `StringBuilder::new` constructs a different object.
   Changing the first builder does not change the second.

## Why pass a Supplier instead of a value?

Compare these snippets, assuming `buildLabel()` is a method returning a string:

```java
String label = buildLabel();
```

```java
Supplier<String> label = () -> buildLabel();
```

The first calls the method while evaluating the assignment. The second
lets a later caller decide when to call it, through `label.get()`.
A helper accepting `Supplier<String>` can decide whether and how often
it needs the value. The supplier's lambda decides how to produce it.

That is why a stream generator accepts a supplier: it needs a recipe for
the next value as processing requests elements.

## Deferred execution does not imply caching

A supplier does not automatically remember its first result. Calling `get`
again runs the supplied implementation again. Caching would need to be
implemented explicitly.

It also does not guarantee a fresh object. These alternative factories differ:

```java
Supplier<StringBuilder> fresh = StringBuilder::new;
```

```java
StringBuilder shared = new StringBuilder();
Supplier<StringBuilder> same = () -> shared;
```

`fresh.get()` constructs a new builder. `same.get()` returns the captured
builder each time. The body determines freshness, randomness, and caching;
the interface only specifies how to request a result.

## Method references and nearby interfaces

- `Math::random` fits `Supplier<Double>`; the returned primitive is boxed.
- `LocalDateTime::now` fits `Supplier<LocalDateTime>`.
- A constructor reference such as `ArrayList::new` can supply a suitable list.
- `Runnable` also accepts no arguments, but its `run()` contract returns `void`.
- `Callable<T>` returns a value through `call()` and permits checked exceptions.
  The `Supplier.get()` declaration does not declare a checked exception.

## Practise and revise

Exercise: [SupplierExercise.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/SupplierExercise.java).
Review: [your supplier attempt](../../exercise-reviews/functional-interfaces/supplier.md).
Stream connection: [generate and limit](../streams/creating-streams.md#generate-and-limit).

Before moving on, explain why your supplier variable and the result of
calling `get()` have different types. Predict what happens after a second
`get`, and explain when a factory returns a fresh object versus a shared one.

Reference: [Supplier API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/function/Supplier.html).

