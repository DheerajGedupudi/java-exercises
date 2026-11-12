# Reduction: combine values into one result

[Home](../../README.md) · [Terminal operations](terminal-operations-and-collection.md) · [BiFunction](../functional-interfaces/bifunction.md)

## Purpose and basic contract

`reduce` combines stream elements into one result, such as an integer total
or product. It is a terminal operation: it consumes the stream and returns
the reduced value rather than another stream.

The first form to learn is `reduce(identity, accumulator)`. Here the identity
has the stream's element type, and the accumulator is a `BinaryOperator<T>`:
two values of type `T` go in and a combined `T` comes back.

`BinaryOperator<T>` extends `BiFunction<T, T, T>`. This is another application
of the two-input contract you have already seen.

## Read the sequential accumulation

For input values `2`, `4`, and `6`, adding with identity zero proceeds like this:

```text
start: 0
combine 0 and 2 → 2
combine 2 and 4 → 6
combine 6 and 6 → 12
```

In this sequential walkthrough, think of the parameters as the running
result and the next input. The combining operation's contract also permits
grouping, so it must work as an associative combination of values.

## A complete example

```java
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class ReductionDemo {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(2, 4, 6);
        BinaryOperator<Integer> add = (left, right) -> left + right;

        int sum = numbers.stream().reduce(0, add);
        int product = numbers.stream().reduce(1, (left, right) -> left * right);

        List<Integer> noNumbers = List.of();
        Optional<Integer> missing = noNumbers.stream().reduce(add);

        System.out.println(sum);
        System.out.println(product);
        System.out.println(missing.isPresent());
        System.out.println(missing.orElse(0));
    }
}
```

Output:

```text
12
48
false
0
```

`Integer::sum` can replace the addition lambda. Each terminal operation
uses a fresh stream. The empty example demonstrates the different result
type when no identity is supplied.

## Identity is a neutral value

In `reduce(identity, accumulator)`, the identity is the **neutral value for
the combining operation**. Combining it with an element must leave that
element unchanged:

```text
accumulator(identity, x) = x
```

For addition, `accumulator` is `(a, b) -> a + b`, so the requirement becomes
`identity + x = x`. Zero satisfies it. For multiplication, it becomes
`identity * x = x`, so one satisfies it.

The identity depends on the operation, not just the stream's element type:

| Operation | Identity | Reason |
|---|---|---|
| Integer addition | `0` | `0 + x = x` |
| Integer multiplication | `1` | `1 * x = x` |
| String concatenation | `""` | `"" + text = text` |

### How it acts as a starting value

These fragments assume `java.util.List` is imported and run inside a method:

```java
int total = List.of(2, 4, 6).stream()
        .reduce(0, (runningTotal, next) -> runningTotal + next);
// total is 12
```

For this sequential stream, the calculation is equivalent to starting a
running total at zero and adding each number:

```text
0 + 2 = 2
2 + 4 = 6
6 + 6 = 12
```

Multiplication uses the same stream elements with a different identity:

```java
int product = List.of(2, 4, 6).stream()
        .reduce(1, (runningProduct, next) -> runningProduct * next);
// product is 48
```

```text
1 * 2 = 2
2 * 4 = 8
8 * 6 = 48
```

It is useful to picture the identity as the starting value in these
sequential walkthroughs, but it cannot be an arbitrary starting amount.
For example, ten is not an identity for addition because `10 + x` changes
`x`. Zero is not an identity for multiplication: starting at zero would make
every product zero. Java does not check that the value you pass satisfies
the identity requirement.

Reduction can also combine separately reduced groups, as in a parallel
stream. A neutral identity lets each group start without changing its
contribution. Treating the identity as an extra amount to add once does
not satisfy that contract.

### What happens to an empty stream?

With no elements, there are no combining steps, so this overload returns
the supplied identity:

```java
int emptySum = List.<Integer>of().stream().reduce(0, Integer::sum);
int emptyProduct = List.<Integer>of().stream().reduce(1, (a, b) -> a * b);
// emptySum is 0; emptyProduct is 1
```

This is why an empty product is one rather than zero in this reduction.
The no-identity overload instead returns an empty Optional, as explained
below.

For revision: explain why both integer reductions above use different
identities. Then predict the result of reducing an empty string stream
with identity `""` and concatenation.

## Associative means grouping does not change the answer

For the operation `op`, the required grouping property is
`op(op(a, b), c) == op(a, op(b, c))`. The integer addition and multiplication
examples satisfy the grouping requirement for this use.

Subtraction does not: `(10 - 3) - 2` is five, while `10 - (3 - 2)` is nine.
An arbitrary loop-update formula is therefore not automatically a valid
two-argument reduction operator. This matters because reduction is not
specified as necessarily running one sequential left-to-right loop.

For later tasks that first transform each element, keep transformation and
combination distinct when using this overload: map each contribution, then
reduce with a valid combining operator. The sum-of-squares task needs that
care; squaring one argument inside an arbitrary binary combining rule can
break the identity and grouping requirements.

### Sum of squares: transform each element, then combine

In a sequential reduction, the left argument is the result accumulated so
far. After several steps it usually represents multiple original elements.
Squaring that result again changes work already completed.

For example, `(a, b) -> a * a + b * b` with identity zero and inputs
`1`, `2`, `3` proceeds like this:

```text
0² + 1² = 1
1² + 2² = 5
5² + 3² = 34   ← the accumulated 5 is squared again
```

The intended result is `1 + 4 + 9 = 14`. Using a wider numeric type would
not correct the calculation.

Map each original element to its contribution, then add the contributions.
This fragment assumes the exercise's `List<Integer> numbers` is in scope:

```java
int sumOfSquares = numbers.stream()
        .map(n -> n * n)
        .reduce(0, (a, b) -> a + b);
```

For inputs one through ten, the result is `385`. Mapping changes the inputs
to `1, 4, 9, ..., 100`. Reduction then uses ordinary addition: zero is its
identity and the operation is associative. `Integer::sum` is an equivalent
replacement for the addition lambda.

Changing only the original lambda to `(total, next) -> total + next * next`
would produce the desired answer for a sequential walkthrough, but it is
still not a valid operator for the two-argument overload. Its identity test
already fails: applying it to `0` and `3` returns `9`, not `3`. The operation
is also not associative. Keep the mapping step when using this overload.

## The form without an identity returns Optional

Read the [Optional chapter](../foundations/optional.md) for factories,
fallbacks, actions, transformations, and complete runnable examples.

`reduce(accumulator)` returns an `Optional<T>` because an empty source has
no first element from which to form a result. An Optional can contain a
value or be empty; it is not the reduced value itself.

### Two overloads, two result contracts

An overloaded method has the same name with different parameter lists.
These are two forms of `reduce` you can choose between:

| Form on Stream<T> | Returned type | Empty-input result |
|---|---|---|
| `reduce(identity, accumulator)` | T | The supplied identity |
| `reduce(accumulator)` | Optional<T> | An empty Optional |

Omitting the identity selects another overload; Java does not automatically
choose an identity for you. In a sequential walkthrough of the no-identity
form, the first element becomes the initial accumulated result. Combining
begins with the second element.

For values `[2, 4, 6]` and addition:

```text
With identity 0:
start 0 → add 2 → 2 → add 4 → 6 → add 6 → 12
returned value: 12

Without an identity:
start with first element 2 → add 4 → 6 → add 6 → 12
returned Optional: contains 12
```

With a single element, such as `[7]`, the no-identity form returns an
Optional containing seven without calling the combining function. With no
elements it returns an empty Optional, not an Optional containing zero.

For `Stream<Integer>`, the identity-based overload returns Integer, which
Java can unbox when assigning to an int. The no-identity overload returns
`Optional<Integer>`; decide explicitly how to handle possible absence.
The combining function must still be associative in both forms.

`isPresent()` asks whether a value exists. `orElse(fallback)` obtains the
value when present and otherwise uses the chosen fallback. The example
chooses zero for an absent sum. Choose an appropriate fallback for the
actual task. Printing an Optional directly shows the container form rather
than just the contained result.

### Joining words with separators

These fragments belong inside a method with `List<String> words` in scope
and `java.util.Optional` imported:

```java
Optional<String> sentence = words.stream()
        .reduce((left, right) -> left + " " + right);
System.out.println(sentence.orElse(""));
```

For `["Java", "Streams", "Are", "Awesome"]`, the printed result is
`Java Streams Are Awesome`. With no explicit identity, the first word starts
the sequential reduction. Subsequent words are combined with a separator.
An empty stream returns an empty Optional; `orElse("")` chooses an empty
string for that case. A one-word stream returns that word unchanged.

Using `reduce("", (left, right) -> left + " " + right)` introduces a leading
space: the first step is `"" + " " + "Java"`. This also violates the identity
requirement: combining `""` with `"Java"` produces `" Java"`, not `"Java"`.
Empty text is neutral for plain concatenation, not for this separator rule.

## Alternatives after practising reduce

The following fragments assume the exercise's `numbers` and `words` lists
are in scope. Each expression creates and consumes its own stream.

### Sum: equivalent method reference or a primitive-stream terminal

```java
int sum = numbers.stream().reduce(0, Integer::sum);
```

`Integer::sum` implements the same addition as `(a, b) -> a + b`.
The lambda is already clear and acceptable; the reference is optional.

For a task that does not specifically require `reduce`:

```java
int sum = numbers.stream().mapToInt(Integer::intValue).sum();
```

`mapToInt` maps each boxed Integer to a primitive int and returns an
IntStream. Its terminal `sum()` adds those ints and returns an int, including
zero for an empty stream. See [IntStream](intstream.md).

### Joining: use a collector dedicated to separators

With `java.util.stream.Collectors` imported:

```java
String sentence = words.stream().collect(Collectors.joining(" "));
```

`Collectors.joining(" ")` supplies a collector that joins text elements in
encounter order, placing a space between adjacent elements. The terminal
`collect` returns a String. This directly expresses joining and avoids
building a new accumulated immutable string on each reduction step.
It is a useful application-code alternative, but it does not practise the
no-identity `reduce` requested in TODO 3.

### Longest word: compare a property with max

With `java.util.Comparator` imported:

```java
String longest = words.stream()
        .max(Comparator.comparingInt(String::length))
        .orElse("");
```

`Comparator.comparingInt(String::length)` builds a Comparator that compares
the lengths of two strings. `max` consumes the stream and returns an
Optional containing a word of greatest length, or an empty Optional for
empty input. Here `orElse("")` supplies an empty-string fallback.

The exercise's reduction with `(a, b) -> a.length() > b.length() ? a : b`
and identity `""` is also valid. For equal lengths it keeps the right-hand
word; in the sequential exercise that means the later tied word. Both
`Streams` and `Awesome` have length seven, so either is a longest word.
The checked `max` alternative returned `Streams`, while that reduction
returned `Awesome`. If a particular tie rule matters, specify it explicitly
rather than assuming different formulations select the same tied word.

## Practise and revise

Exercise: [ReduceBasics.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/ReduceBasics.java).
Start with TODOs 1–2: identify the identity and combining rule, predict the
result, then run and print it. Before TODOs using the no-identity form,
explain why the return type is Optional and how you will obtain its result.

For each reduction, identify the result type, the combining function's
two input types, the neutral value if supplied, and the empty-input behavior.

After attempting the full exercise, revisit these questions:

- Does the left argument still represent one original element after several steps?
- Why is `""` not neutral for a rule that always inserts a space?
- Why does `total + next * next` fail the two-argument identity test?
- What happens when two longest words have equal lengths?

Reference: [Stream.reduce API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html#reduce(T,java.util.function.BinaryOperator)).
Related APIs: [Stream.max](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html#max(java.util.Comparator)),
[Collectors.joining](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Collectors.html#joining(java.lang.CharSequence)).

