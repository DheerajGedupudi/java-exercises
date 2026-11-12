# Optional: represent a value that might be absent

[Home](../../README.md) · [Reduction](../streams/reduction.md) · [Functional interfaces](functional-interfaces.md)

## Start here: Optional is a class

`java.util.Optional<T>` is a class, not an interface or a Java keyword.
Import it with `import java.util.Optional;`.

Read `Optional<String>` as: **this result might contain a String, or it
might contain no value**. The type argument describes the contained value.

```text
String                 → a string reference, which Java also allows to be null
Optional<String>       → an object representing a present string or no value
```

An empty Optional is still an object. It is different from a null reference.
For example, an unsuccessful search can return `Optional.empty()` so the
caller can decide whether to use a fallback, take no action, or throw.

This class does not make method parameters optional. It represents absence
in a value, especially a method's return value.

## Create one: of, ofNullable, and empty

| Factory | Example | Result |
|---|---|---|
| `of(value)` | `Optional.of("Java")` | Present string; null would throw `NullPointerException` |
| `ofNullable(value)` | `Optional.ofNullable(possiblyNull)` | Present if non-null, otherwise empty |
| `empty()` | `Optional.empty()` | No value |

Use these factories rather than `new Optional(...)`.

The examples below that are fragments assume `Optional` is imported and
the statements run inside a method. Each complete program includes its
own imports and expected output.

```java
String possiblyNull = null;
Optional<String> name = Optional.ofNullable(possiblyNull);
// name is empty
```

Keep the Optional reference itself non-null. Assigning null to it would
bring back the need to check whether you can even call its methods.

`Optional.of("")` is **present**: an empty string is a string value. Similarly,
`Optional.of(0)` contains the number zero. Neither means absence.

## A first complete example

```java
import java.util.Optional;

public class OptionalBasicsDemo {
    public static void main(String[] args) {
        Optional<String> present = Optional.of("Java");
        Optional<String> absent = Optional.empty();

        String possiblyNull = null;
        Optional<String> fromNullable = Optional.ofNullable(possiblyNull);

        System.out.println(present);
        System.out.println(absent);
        System.out.println(present.isPresent());
        System.out.println(absent.isEmpty());
        System.out.println(present.orElse("Unknown"));
        System.out.println(absent.orElse("Unknown"));
        System.out.println(fromNullable.isEmpty());
        System.out.println(Optional.of("").isPresent());
    }
}
```

Expected output with this project's JDK:

```text
Optional[Java]
Optional.empty
true
true
Java
Unknown
true
true
```

Printing the Optional displays its container representation. Calling
`orElse` here obtains a String that you can print or otherwise use.
The container's display text is for inspection, not a format to parse.

## Choose how to handle the result

For `Optional<T> result`:

| Method | Input | Result or effect |
|---|---|---|
| `isPresent()` | Nothing | boolean: whether a value exists |
| `isEmpty()` | Nothing | boolean: whether no value exists |
| `orElse(fallback)` | A value of type T | Contained T, or the fallback |
| `orElseGet(supplier)` | A Supplier producing T | Contained T, or a supplied fallback |
| `orElseThrow()` | Nothing | Contained T; throws `NoSuchElementException` if empty |
| `ifPresent(action)` | A Consumer of T | Runs the action if present; returns void |
| `get()` | Nothing | Contained T; throws `NoSuchElementException` if empty |

### A fallback is your decision

```java
Optional<String> result = Optional.empty();
String text = result.orElse("No result");
System.out.println(text); // No result
```

`orElse` returns a value; it does not fill or change the empty Optional.
The example chooses a display message. Other callers might choose a
different default or decide that absence should cause an exception.

### If absence is an error

```java
Optional<String> result = Optional.of("Java");
String text = result.orElseThrow();
```

For a meaningful custom exception, the overload accepts an exception
Supplier. This is a fragment using the `result` above:

```java
String requiredText = result.orElseThrow(
        () -> new IllegalStateException("A result is required"));
```

The lambda creates the exception only when the Optional is empty.

### If you want an action rather than a returned value

```java
Optional<String> result = Optional.of("Java");
result.ifPresent(System.out::println); // Java
```

The action receives the contained String. With an empty Optional, the action
does not run. `ifPresent` returns void, so use a value-returning method when
you need to assign the result.

To act in both cases, `ifPresentOrElse` accepts a Consumer for the value
and a Runnable for the empty case:

```java
Optional<String> result = Optional.empty();
result.ifPresentOrElse(
        text -> System.out.println("Found: " + text),
        () -> System.out.println("Nothing found"));
// Nothing found
```

### What about get?

`get()` works when the value exists. Calling it without accounting for an
empty result can throw. A presence check followed by `get()` is valid, but
`ifPresent`, a fallback, or an explicit `orElseThrow` often states the intent
more directly.

You have seen `Supplier.get()` too. The shared method name does not make
Optional a Supplier: `Supplier.get()` runs supplied behavior; `Optional.get()`
reads a value that must already be present. Compare [Supplier](../functional-interfaces/supplier.md).

## orElse versus orElseGet: a value versus deferred behavior

Compare these fragments, where `result` is an Optional and `buildFallback`
is a method returning a String:

```java
result.orElse(buildFallback());
result.orElseGet(() -> buildFallback());
```

In the first line, Java evaluates the method argument `buildFallback()`
before calling `orElse`. That happens even when the fallback is not needed.

In the second line, the argument is a Supplier. Its body is called only
if the Optional is empty. This is the same define-versus-invoke distinction
from your Supplier exercises.

```java
import java.util.Optional;

public class OptionalFallbackDemo {
    private static String buildFallback() {
        System.out.println("Creating fallback");
        return "Guest";
    }

    public static void main(String[] args) {
        Optional<String> present = Optional.of("Java");
        Optional<String> absent = Optional.empty();

        System.out.println(present.orElse(buildFallback()));
        System.out.println(present.orElseGet(() -> buildFallback()));
        System.out.println(absent.orElseGet(() -> buildFallback()));
    }
}
```

Output:

```text
Creating fallback
Java
Java
Creating fallback
Guest
```

Use `orElse("Guest")` freely for a simple default value. Use `orElseGet`
when fallback creation should wait until it is needed.

## Transform or test a contained value

These methods connect directly to interfaces you have practised:

| Method on Optional<T> | Behavior accepted | Result |
|---|---|---|
| `map(mapper)` | Function from T to U | Optional<U> |
| `filter(predicate)` | Predicate testing T | Optional<T>, possibly empty |
| `ifPresent(action)` | Consumer acting on T | void |
| `orElseGet(supplier)` | Supplier producing T | T |

`map` transforms an existing value. `filter` keeps it only if its test passes.
For an empty Optional, neither runs its callback. A null result from a
`map` callback becomes an empty Optional.

```java
import java.util.Optional;

public class OptionalTransformDemo {
    public static void main(String[] args) {
        Optional<String> text = Optional.of("  Java  ");

        Optional<Integer> length = text
                .map(String::strip)
                .filter(value -> !value.isEmpty())
                .map(String::length);

        System.out.println(length);
        System.out.println(length.orElse(0));
        text.map(String::strip).ifPresent(System.out::println);

        Optional<String> blank = Optional.of("  ");
        blank.map(String::strip)
                .filter(value -> !value.isEmpty())
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("No text"));

        Optional<String> absent = Optional.empty();
        System.out.println(absent.map(String::length).orElse(0));
    }
}
```

Expected output with this project's JDK:

```text
Optional[4]
4
Java
No text
0
```

Follow the types and values in the first chain:

```text
Optional<String>   containing "  Java  "
       map(strip)
Optional<String>   containing "Java"
       filter(non-empty)
Optional<String>   containing "Java"
       map(length)
Optional<Integer>  containing 4
       orElse(0)
int               4, after unboxing the returned Integer
```

Each call returns its result; the original `text` still contains the
original string with spaces. In the blank case, stripping produces `""`,
then the filter makes the result empty.

These methods use [Function](../functional-interfaces/function.md),
[Predicate](../functional-interfaces/predicate.md), and
[Consumer](../functional-interfaces/consumer.md). Optional itself is not a
functional interface; you pass lambdas to its methods.

## Connect it to your reduce exercise

The no-identity reduction has to represent the case where there were no
elements from which to form a result:

```text
Stream<String>
    reduce(combiningFunction)
Optional<String>
    orElse(fallbackString)
String
```

The stream has already been consumed when `reduce` returns. After that,
calls such as `map`, `ifPresent`, or `orElse` operate on the Optional.
See [ReduceBasics.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/ReduceBasics.java)
and the [reduction chapter](../streams/reduction.md#the-form-without-an-identity-returns-optional).

Identity-based reduction already has a result for empty input: its identity.
For `Stream<Integer>`, that overload returns an Integer, which can be
unboxed into an int. Without an identity it returns `Optional<Integer>`.
Choosing a fallback of zero for an absent sum is a caller's decision, not
a value automatically stored inside an empty Optional.

Optional is not a lazy Stream. For a present Optional, its `map` and `filter`
callbacks run during those method calls; there is no terminal operation
needed to trigger them. The deferred Supplier in `orElseGet` is a specific
method contract, not a rule that all Optional operations are lazy.

## Later reference: flatMap when the function already returns Optional

First get comfortable with `map` and extraction. This section explains a
related operation you will meet later.

Suppose parsing a string returns `Optional<Integer>`: a parsed number on
success, or empty when the text is not an integer. Applying ordinary `map`
to an `Optional<String>` would wrap that Optional result again:

```text
map:      Function<String, Optional<Integer>> → Optional<Optional<Integer>>
flatMap:  Function<String, Optional<Integer>> → Optional<Integer>
```

`flatMap` accepts a function whose result is already an Optional and avoids
adding the extra container. It also leaves empty input empty. The function
must return an Optional object, not null.

```java
import java.util.Optional;

public class OptionalFlatMapDemo {
    private static Optional<Integer> parseNumber(String text) {
        try {
            return Optional.of(Integer.parseInt(text));
        } catch (NumberFormatException exception) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        Optional<String> input = Optional.of("42");
        Optional<Optional<Integer>> nested = input.map(OptionalFlatMapDemo::parseNumber);
        Optional<Integer> number = input.flatMap(OptionalFlatMapDemo::parseNumber);

        System.out.println(nested);
        System.out.println(number.orElse(0));
        System.out.println(Optional.of("oops").flatMap(OptionalFlatMapDemo::parseNumber));
    }
}
```

Expected output with this project's JDK:

```text
Optional[Optional[42]]
42
Optional.empty
```

The parser deliberately treats an invalid number as an absent result. That
is this example method's policy. Optional does not automatically catch
exceptions from arbitrary callbacks.

## Recap and self-check

- Optional represents a present value or no value; it is a class.
- Choose a fallback, an action, or an exception to handle the empty case.
- Track the return type: `map` returns an Optional, while `orElse` returns T.
- A present empty string and an empty Optional mean different things.
- For generic numeric values use `Optional<Integer>`, not `Optional<int>`.
  Primitive-stream APIs also have separate classes such as OptionalInt;
  those are covered when needed alongside [IntStream](../streams/intstream.md).

Try answering these before opening the answers:

1. Is `Optional.of("")` empty? What does `isPresent()` return?
2. After `Optional.of("Java").map(String::length)`, what is the type?
3. What does `orElse(0)` return on that result? Does it change the Optional?
4. Why can `orElse(buildFallback())` run the helper when a value is present?
5. Why does the no-identity `reduce` need an Optional result?

<details>
<summary>Answers</summary>

1. It contains an empty string, so `isPresent()` is true.
2. `Optional<Integer>`, containing four.
3. The returned Integer is four; it can unbox into int. The Optional is unchanged.
4. Java evaluates that argument before invoking `orElse`; use a Supplier
   with `orElseGet` to defer the helper call until absence is known.
5. Empty input supplies neither an identity nor a first element from which
   to form a result. The returned Optional represents that absence.

</details>

Reference: [Java 25 Optional API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Optional.html).
