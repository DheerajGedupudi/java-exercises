# Lambdas: parameters, body, and invocation

[Home](../../README.md) · Previous: [Functional interfaces](functional-interfaces.md) · Next: [Anonymous classes](anonymous-classes.md)

## Read a lambda in context

```java
Function<Integer, Integer> twice = (Integer number) -> number * 2;
```

This snippet assumes `java.util.function.Function` is imported.

| Part | Meaning |
|---|---|
| `Function<Integer, Integer>` | Target interface: one integer input and an integer result |
| `twice` | Variable holding the behavior |
| `(Integer number)` | Parameter declaration |
| `->` | Separates parameters from the body |
| `number * 2` | Expression supplying the result when invoked |

The lambda implements `apply`, the method declared by `Function`. Naming
the variable `twice` does not create a method called `twice`; invoke it with
`twice.apply(5)`.

## Full and shorter forms

These are alternative forms of the same value-returning behavior:

```java
(Integer number) -> {
    return number * 2;
}
```

```java
(Integer number) -> number * 2
```

```java
number -> number * 2
```

The surrounding target supplies the types, so they can be inferred. A single
parameter with an inferred type can omit parentheses. With zero parameters
use `()`, and with two or more use parentheses: `(a, b)`.

For a value-returning contract, an expression body supplies the result directly.
A normal block body returning a value uses `return`. Removing the braces
also removes that `return` keyword. Multiple statements require a block.

For a `void` contract, a method-call expression can perform the action:

```java
Consumer<String> printer = text -> System.out.println(text);
```

Its block form is `text -> { System.out.println(text); }`. The statement
semicolon is inside the block. In a variable declaration, another semicolon
ends the declaration after the closing brace.

## Assign the lambda directly

For your custom `Greeting` interface, the assignment shape is
`Greeting greeting = name -> ...;`. The ellipsis here is a placeholder for
your body, not Java code to paste.

`new Greeting(lambda)` is not the lambda syntax. The interface is not a
constructor accepting your lambda. An anonymous implementation instead uses
`new Greeting() { ... }` with a whole method declaration inside the class body.

## A complete example

```java
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdasDemo {
    public static void main(String[] args) throws Exception {
        Function<Integer, Integer> twice = number -> number * 2;
        Supplier<String> message = () -> "ready";
        Consumer<String> printer = text -> System.out.println(text);
        Runnable action = () -> System.out.println("done");
        Callable<String> task = () -> "computed";

        System.out.println(twice.apply(5));
        printer.accept(message.get());
        action.run();
        System.out.println(task.call());
    }
}
```

Output:

```text
10
ready
done
computed
```

The declarations define the behavior. `apply`, `get`, `accept`, `run`, and
`call` invoke the respective contracts. `Callable.call()` can throw a checked
exception; this example's `main` declares `throws Exception`.

## Returning and printing are separate

`task.call()` evaluates the lambda and returns a string. If you discard that
result, nothing in this particular lambda prints it. `println(task.call())`
uses the return value as the argument to another method.

The body of `action` explicitly calls `println`, so `action.run()` prints.
A `Callable` body can also print if you write that action into it. The
difference comes from the implementation as well as the interface contract.

## Parameter scope

A lambda parameter cannot shadow an enclosing local variable. This fragment
is invalid inside one method:

```java
String text = "already declared";
Function<String, Integer> length = text -> text.length(); // invalid
```

Rename one of the declarations, for example `value -> value.length()`.
The earlier variable remains in scope after you have used it.

Lambdas can read enclosing local variables that are final or effectively final.
For example, `String prefix = "item:";` can be used in `value -> prefix + value`
provided you do not reassign `prefix`. Revisit capture when an exercise needs it.

## Your sorting example

A comparator result is negative, zero, or positive according to the ordering.
Prefer `Integer.compare(a.value, b.value)` over subtraction: subtraction can
overflow and produce the wrong sign. For a tie, compare the indices with
`Integer.compare(a.index, b.index)`.

Exercise: [LambdaExpressions.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_1_gettingstarted/LambdaExpressions.java).
Reviews: [lambda exercises](../../exercise-reviews/functional-interfaces/lambda-expressions.md)
and [the scope error](../../exercise-reviews/functional-interfaces/method-references.md).

Before moving on, explain which declarations run immediately, which lambda
bodies run later, and why a returned string does not automatically print.

