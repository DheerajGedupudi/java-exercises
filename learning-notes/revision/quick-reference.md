# Quick reference

[Home](../README.md) · [Self-check](self-check.md)

Use this to recall syntax after reading the chapters. The individual snippets
are examples, not one combined program. Import the relevant types from
`java.util.function`; `Comparator` is in `java.util`.

## Choose the contract

| Interface | Inputs | Result | Method | Chapter |
|---|---|---|---|---|
| `Supplier<T>` | None | `T` | `get()` | [Supplier](../explanations/functional-interfaces/supplier.md) |
| `Consumer<T>` | One `T` | `void` | `accept(T)` | [Consumer](../explanations/functional-interfaces/consumer.md) |
| `Function<T, R>` | One `T` | `R` | `apply(T)` | [Function](../explanations/functional-interfaces/function.md) |
| `Predicate<T>` | One `T` | `boolean` | `test(T)` | [Predicate](../explanations/functional-interfaces/predicate.md) |
| `BiFunction<T, U, R>` | `T`, `U` | `R` | `apply(T, U)` | [BiFunction](../explanations/functional-interfaces/bifunction.md) |
| `BiConsumer<T, U>` | `T`, `U` | `void` | `accept(T, U)` | [Consumer](../explanations/functional-interfaces/consumer.md#biconsumer-two-inputs-no-result) |
| `BiPredicate<T, U>` | `T`, `U` | `boolean` | `test(T, U)` | [Predicate](../explanations/functional-interfaces/predicate.md#bipredicate-and-stream-filtering) |
| `Comparator<T>` | Two `T` values | Comparison `int` | `compare(T, T)` | [Lambdas](../explanations/foundations/lambdas.md#your-sorting-example) |

`Runnable.run()` takes no arguments and returns `void`.
`Callable<T>.call()` takes no arguments, returns `T`, and permits checked exceptions.

## Declaration and invocation

```java
Supplier<String> supply = () -> "ready";
String result = supply.get();

Consumer<String> print = text -> System.out.println(text);
print.accept("ready");

Function<String, Integer> length = text -> text.length();
int size = length.apply("java");

Predicate<String> empty = text -> text.isEmpty();
boolean answer = empty.test("");

BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
int total = add.apply(2, 5);
```

## Lambda syntax

| Parameter shape | Syntax |
|---|---|
| No parameters | `() -> expression` |
| One inferred parameter | `value -> expression` |
| One explicit parameter | `(String value) -> expression` |
| Two inferred parameters | `(a, b) -> expression` |
| Value-returning block | `value -> { return expression; }` |
| Several actions | `value -> { statement; statement; }` |

The table uses placeholders such as `expression`; substitute actual Java
expressions. Assign directly to the functional-interface variable. A lambda
parameter cannot shadow an enclosing local variable.

## Composition

| Expression | Flow when invoked |
|---|---|
| `first.andThen(second)` on functions | Input → first result → second result |
| `first.compose(before)` | Input → before result → first result |
| `first.andThen(second)` on consumers | Original input to first action, then to second action |
| `first.and(second)` on predicates | Both must be true; short-circuit after false |
| `first.or(second)` on predicates | One must be true; short-circuit after true |
| `predicate.negate()` | Reverse the boolean answer |

Composition produces behavior. Invoke its method to run it.

## Method references

| Context | Reference | Lambda |
|---|---|---|
| Static conversion | `String::valueOf` | `value -> String.valueOf(value)` |
| A known output object | `System.out::println` | `text -> System.out.println(text)` |
| Method on the input string | `String::length` | `text -> text.length()` |
| Factory for a list | `ArrayList::new` | `() -> new ArrayList<String>()` |

Use a compatible target type; these are context-dependent expressions.

## Streams

| Operation | Role |
|---|---|
| `list.stream()` / `Arrays.stream(array)` | Create a source |
| `Stream.generate(supplier)` | Supply an unbounded source on demand |
| `filter(predicate)` | Keep matching values |
| `map(function)` | Transform values |
| `limit(n)` | Bound the element count |
| `forEach(consumer)` | Terminal action |
| `collect(Collectors.toList())` | Terminal list accumulation |
| `IntStream.range(a, b)` | Primitive ints from `a`, excluding `b` |
| `IntStream.rangeClosed(a, b)` | Primitive ints including both endpoints |
| `IntStream.boxed()` | Convert to `Stream<Integer>` |
| `IntStream.mapToObj(...)` | Convert to an object stream |

After a terminal operation, create a fresh stream for another traversal.
For primitive-array sources and character codes, see [IntStream](../explanations/streams/intstream.md).

