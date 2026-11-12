# Method references: naming existing behavior

[Home](../../README.md) · Previous: [Anonymous classes](anonymous-classes.md) · Next: [Supplier](../functional-interfaces/supplier.md)

## What the syntax means

When a lambda forwards to a compatible existing method, a method reference
can often express that behavior directly. The `::` operator supplies the
reference; it does not invoke the referenced method immediately.

The target functional interface still determines the required inputs and
result. `String::toUpperCase` can supply a `Function<String, String>`; calling
that function's `apply` method invokes the conversion.

## The four forms

| Form | Example | Corresponding lambda in the shown target context |
|---|---|---|
| Static method | `String::valueOf` | `value -> String.valueOf(value)` for `Function<Integer, String>` |
| Instance method on a particular object | `System.out::println` | `text -> System.out.println(text)` for `Consumer<String>` |
| Instance method on the input object | `String::length` | `text -> text.length()` for `Function<String, Integer>` |
| Constructor | `ArrayList::new` | `() -> new ArrayList<String>()` for `Supplier<List<String>>` |

For `String::length`, the input string becomes the receiver of `length()`.
For `System.out::println`, the receiver is already the particular `System.out`
object; the functional argument becomes the argument to `println`.

Consequently, `ClassName::method` does not automatically mean a static method.
`String::length` refers to an instance method on a future input object.

## A complete example

```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferencesDemo {
    public static void main(String[] args) {
        Function<Integer, String> stringify = String::valueOf;
        Consumer<String> printer = System.out::println;
        Function<String, String> uppercase = String::toUpperCase;
        Function<String, Integer> length = String::length;
        Supplier<List<String>> lists = ArrayList::new;

        System.out.println(stringify.apply(42));
        printer.accept("hello");
        System.out.println(uppercase.apply("hello"));
        System.out.println(length.apply("java"));
        System.out.println(lists.get());
    }
}
```

Output:

```text
42
hello
HELLO
4
[]
```

The variables hold behavior. The later `apply`, `accept`, and `get` calls
invoke it. `ArrayList::new` creates an empty list when `get` is called.

## IntelliJ's suggestion

Replacing `name -> name.toUpperCase()` with `String::toUpperCase` is an
optional simplification in this context. The original lambda remains valid.
Choose the form you can explain clearly.

A lambda adding extra work needs to preserve that work. For example,
`text -> System.out.println("item: " + text)` includes formatting that a bare
`System.out::println` would not perform.

Overloaded methods are resolved using the target contract. Look first at
the expected functional interface when a reference is confusing or ambiguous.

## Practise and revise

Exercise: [MethodReferences.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_1_gettingstarted/MethodReferences.java).
Review: [all six TODOs and the earlier scope error](../../exercise-reviews/functional-interfaces/method-references.md).

Write an equivalent lambda for each reference in the table. Identify which
value is the receiver and which values are method arguments. Then explain
why the reference needs a target type and why defining it does not run it.

