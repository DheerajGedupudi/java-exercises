# Functional interfaces: passing behavior as a value

[Home](../../README.md) · [Study order](../../learning-plan.md) · Next: [Lambdas](lambdas.md)

## Why they exist

You already pass data to methods. Sometimes a method also needs a decision
or an action supplied by its caller. Sorting is the familiar example: the
list supplies the elements, while your comparator supplies the ordering.

A functional interface gives that behavior a type. It has one abstract
method contract to implement, so a lambda can supply that implementation.
Methods matching public methods on `Object` are excluded from the functional
method count. Default and static methods do not add abstract contracts.

## A complete example

```java
public class FunctionalInterfaceDemo {
    @FunctionalInterface
    interface Label {
        String format(String text);
    }

    static String render(Label formatter, String text) {
        return formatter.format(text);
    }

    public static void main(String[] args) {
        Label brackets = text -> "[" + text + "]";
        System.out.println(render(brackets, "java"));
    }
}
```

Output:

```text
[java]
```

Read the execution in order:

1. `Label` declares a method taking a string and returning a string.
2. The lambda supplies the body of `format` for `brackets`.
3. `render` receives that behavior and the separate data value `"java"`.
4. `formatter.format(text)` invokes the supplied behavior.
5. The returned string reaches `println`.

The helper controls when the behavior is invoked. The lambda controls what
the behavior does. This is also the pattern behind `sort`, `forEach`, and
many stream methods.

## What the type on the left tells Java

In `Label brackets = text -> "[" + text + "]"`, `Label` is the target type.
It tells Java which method the lambda implements and what parameter and
return types it must satisfy. Parameter names such as `text` are your choice.

An interface-typed variable refers to an object implementing the interface.
It can hold an instance of a named class, an anonymous class, or a compatible
lambda implementation. See [anonymous classes](anonymous-classes.md).

`@FunctionalInterface` asks the compiler to check the contract. A valid
functional interface can work without that annotation; the annotation does
not provide the method body.

## The standard library provides reusable contracts

`Supplier`, `Consumer`, `Function`, `Predicate`, and `BiFunction` are ordinary
interface names from `java.util.function`. They are library types, like
`Comparator`, rather than Java keywords such as `class` or `return`.

| Need | Contract | Method to invoke |
|---|---|---|
| Obtain a value without passing an argument | [Supplier](../functional-interfaces/supplier.md) | `get()` |
| Give a value to an action | [Consumer](../functional-interfaces/consumer.md) | `accept(value)` |
| Transform a value | [Function](../functional-interfaces/function.md) | `apply(value)` |
| Ask a yes/no question about a value | [Predicate](../functional-interfaces/predicate.md) | `test(value)` |
| Compute a result from two values | [BiFunction](../functional-interfaces/bifunction.md) | `apply(first, second)` |

The interface name does not determine the actual calculation. Your lambda
or implementing class supplies it. A `BiFunction` might add numbers, join
strings, or do another compatible operation.

## Common confusions

- Declaring an interface defines the contract; assigning a lambda supplies
  behavior; invoking its method runs that behavior. These are separate steps.
- `public abstract` is implicit on an ordinary abstract interface method
  such as `String format(String text);`.
- A variable holding behavior is different from the value obtained by invoking it.
- Different interfaces can have similar-looking lambdas. The required target
  contract decides whether a particular lambda is compatible.

## Practise and revise

Exercise: [FunctionalInterfaceBasics.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_1_gettingstarted/FunctionalInterfaceBasics.java).
Review: [your earlier attempt](../../exercise-reviews/functional-interfaces/functional-interface-basics.md).

Explain why `render` accepts both a `Label` and a `String`. Then identify
the exact line that invokes the lambda, rather than merely passing it around.

Reference: [Java functional-interface package](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/function/package-summary.html).

