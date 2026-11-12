# Self-check: explain and predict

[Home](../README.md) · [Quick reference](quick-reference.md)

Answer before opening each explanation. The goal is to connect the contract,
the syntax, and execution. Looking up a signature afterward is fine.

## 1. Pick an interface

Which interfaces fit these tasks: request a fresh builder without arguments,
print a supplied string, turn a string into its length, check if a number
is positive, and combine two strings into a result?

<details>
<summary>Answer</summary>

`Supplier`, `Consumer`, `Function`, `Predicate`, and `BiFunction`, respectively.
Explain the input and result of each instead of only naming it.

</details>

## 2. Define versus invoke

Inside a method, a supplier is defined with a body that prints `inside` and
returns `ready`. Then the program prints `defined`, invokes `get`, and prints
the returned value. What is the output order?

<details>
<summary>Answer</summary>

`defined`, then `inside`, then `ready`. Defining the lambda does not run
its body. A second `get` executes the supplied body again; caching is not
automatic. See [Supplier](../explanations/functional-interfaces/supplier.md).

</details>

## 3. Return versus print

A callable's body only returns `computed`. Does calling `call()` by itself
print it? What two ways could the caller use that return value?

<details>
<summary>Answer</summary>

It does not print it. The caller can assign it to a variable, pass it to
`println`, or otherwise use it. See [lambdas](../explanations/foundations/lambdas.md#returning-and-printing-are-separate).

</details>

## 4. Fresh versus shared

Does the `Supplier` interface guarantee a fresh object on each `get`?
How do a constructor reference and a lambda returning a captured object differ?

<details>
<summary>Answer</summary>

Freshness is determined by the implementation. A constructor reference
constructs an object per invocation; returning one captured object reuses it.
See [freshness and caching](../explanations/functional-interfaces/supplier.md#deferred-execution-does-not-imply-caching).

</details>

## 5. Consumer chaining

One consumer prints `seen:` followed by its input. Another prints the input's
length. When their composition accepts `cat`, does the second receive `cat`
or `seen:cat`?

<details>
<summary>Answer</summary>

It receives the original `cat`, so the length is three. Printed output is
not a returned value passed to the second consumer. See [Consumer](../explanations/functional-interfaces/consumer.md).

</details>

## 6. Function composition

`strip` removes surrounding whitespace and `size` returns string length.
Explain `strip.andThen(size)` and `size.compose(strip)` on `  java  `.
Would `size.andThen(strip)` fit those types?

<details>
<summary>Answer</summary>

The first two strip before measuring and return four. The reversed chain
would give an `Integer` to a function expecting `String`, so it does not fit.
See [Function](../explanations/functional-interfaces/function.md).

</details>

## 7. Two inputs and three type arguments

What does each type in `BiFunction<String, Integer, String>` describe?
Does `andThen` return a calculated value or another function?

<details>
<summary>Answer</summary>

First input is a string, second input an integer, result a string.
`andThen` builds another function; `apply` obtains a result for actual inputs.
See [BiFunction](../explanations/functional-interfaces/bifunction.md).

</details>

## 8. Predicate order

Why should a null check run before a condition calling `isEmpty()`? Does
negating a greater-than-zero test include zero?

<details>
<summary>Answer</summary>

Short-circuiting can prevent the unsafe call on null. Negating greater than
zero includes zero. See [Predicate](../explanations/functional-interfaces/predicate.md).

</details>

## 9. The receiver in a method reference

Is `String::length` a static method reference? What supplies the object?
How is that different from `System.out::println`?

<details>
<summary>Answer</summary>

`length` is an instance method; the incoming string is its receiver.
`System.out::println` already has a specific receiver object and accepts
the value to print. See [method references](../explanations/foundations/method-references.md).

</details>

## 10. Lambda scope

Why does a lambda parameter named `text` conflict with an enclosing local
variable named `text`, even after the earlier variable was printed?

<details>
<summary>Answer</summary>

The local remains in scope. A lambda parameter cannot shadow it. Rename
one declaration. See [parameter scope](../explanations/foundations/lambdas.md#parameter-scope).

</details>

## 11. Stream execution

Which operation starts traversal in a filter/map/collect pipeline? Can the
same stream instance then be counted? How would you traverse the source again?

<details>
<summary>Answer</summary>

`collect` is terminal and drives traversal. The consumed stream cannot be
reused; create a fresh stream from the source. See [pipelines](../explanations/streams/overview.md).

</details>

## 12. Primitive streams and characters

What does `IntStream.range(2, 5)` supply? Why does printing an element from
`"Hi".chars()` directly print a number? Which mapping changes it to an object stream?

<details>
<summary>Answer</summary>

The range supplies 2, 3, and 4. `chars` supplies numeric Java `char` values
as ints. `mapToObj` changes the stream kind; a cast to `char` in that mapping
can supply `Character` elements for this example. See [IntStream](../explanations/streams/intstream.md).

</details>

## 13. Generated sources

What does `generate` receive? Why can `generate(...).limit(3).forEach(...)`
finish even though the source has no end?

<details>
<summary>Answer</summary>

It receives a supplier. The limit bounds the elements consumed by the
terminal action. Values are supplied on demand, rather than an infinite
collection being allocated first. See [creating streams](../explanations/streams/creating-streams.md#generate-and-limit).

</details>

