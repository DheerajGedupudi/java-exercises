# Predicate: a condition on an input

[Home](../../README.md) · Previous: [BiFunction](bifunction.md) · Next: [Stream pipelines](../streams/overview.md)

## Purpose and contract

`Predicate<T>` represents a condition that accepts one value and returns
`true` or `false`. It is from `java.util.function` and declares:

```java
boolean test(T value);
```

The generic argument describes the input. The result is always primitive
`boolean`, so there is no separate result type argument.

After defining `Predicate<String> longEnough = text -> text.length() > 3;`,
invoke it with `longEnough.test("java")`. It returns a boolean; print that
boolean separately if you want to observe it in the console.

## Combining conditions

| Expression | Condition it builds |
|---|---|
| `first.and(second)` | First and second must both succeed |
| `first.or(second)` | At least one must succeed |
| `first.negate()` | Reverse the first condition's answer |

These operations return predicates. They build behavior that you evaluate
later with `test`. Pass another predicate to `and` or `or`, rather than an
already evaluated boolean for one particular input.

## A complete example: guarding a string operation

```java
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> nonNull = text -> text != null;
        Predicate<String> nonEmpty = text -> !text.isEmpty();
        Predicate<String> usable = nonNull.and(nonEmpty);
        Predicate<Integer> positive = number -> number > 0;
        BiPredicate<String, Integer> lengthIs = (text, size) -> text.length() == size;

        System.out.println(usable.test(null));
        System.out.println(usable.test(""));
        System.out.println(usable.test("hi"));
        System.out.println(positive.negate().test(0));
        System.out.println(lengthIs.test("cat", 3));
    }
}
```

Output:

```text
false
false
true
true
true
```

For the null input, `nonNull` returns false, so `and` does not invoke
`nonEmpty`. For `""`, both are evaluated and the second returns false.
For `"hi"`, both return true.

## Evaluation order matters

`and` and `or` short-circuit from the receiver to the argument:

- `and` skips the second predicate when the first is false.
- `or` skips the second predicate when the first is true.

Your positive/even and divisible-by-three checks produce the same boolean
results in either order for the non-null integers in that exercise. That
does not make arbitrary predicate implementations interchangeable.

For example, reversing the null guard above would call `isEmpty()` on null
before checking the guard. Order can affect exceptions, side effects, and
how much work is performed.

`positive.negate()` means not greater than zero. It includes zero as well
as negative numbers; it is different from a strictly negative condition.

## BiPredicate and stream filtering

`BiPredicate<T, U>` declares `boolean test(T first, U second)`. Both generic
arguments are input types; the boolean result is part of the contract.

A regular object stream's `filter` receives a `Predicate`. It keeps an
element when the condition evaluates to true. It receives the condition
itself and invokes it while processing, so you do not manually call `test`
for each list element beforehand.

Primitive streams have matching specialized contracts, such as `IntPredicate`
for `IntStream.filter`. See [primitive streams](../streams/intstream.md).
For an object-stream example, read the [filtering walkthrough](../streams/filtering.md).

## Practise and revise

Exercise: [PredicateExercise.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/PredicateExercise.java).
Review: [all seven TODOs](../../exercise-reviews/functional-interfaces/predicate.md).

Your implementation has already passed review. Explain the null guard,
short-circuiting, why zero is not positive, and the difference between
building a predicate and testing an input. Revisit code where that explanation
reveals a gap.

Reference: [Predicate API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/function/Predicate.html).

