# Minimum and maximum: choose an element by an ordering

[Home](../../README.md) · [Optional](../foundations/optional.md) · [Reduction](reduction.md)

## Purpose and contracts

Use `min` or `max` to select one stream element according to a comparator.
Both are terminal operations and consume the stream.

| Operation on Stream<T> | Argument | Result |
|---|---|---|
| `min(comparator)` | A comparator ordering the elements | Optional<T> containing a minimum element, or empty for empty input |
| `max(comparator)` | A comparator ordering the elements | Optional<T> containing a maximum element, or empty for empty input |

The result contains an original element. Comparing people by age still
returns a person; comparing words by length still returns a word.
See the [Stream min/max contracts](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html#min(java.util.Comparator)).

## A comparator describes ordering

`Comparator<T>` is a functional interface. Its `compare(left, right)`
method takes two T values and returns an int: negative when left comes
before right, zero when they tie under this ordering, and positive when
left comes after right. The result is not a boolean.

| Factory | Purpose | Example |
|---|---|---|
| `Comparator.naturalOrder()` | Follow the element type's natural ordering | Integers in ascending numeric order |
| `Comparator.comparingInt(keyExtractor)` | Compare elements using an extracted int key | `Comparator.comparingInt(String::length)` compares word lengths |

For a Person record with an int age component,
`Comparator.comparingInt(Person::age)` accepts the age-extraction behavior
and returns a Comparator<Person>. It does not transform a stream into
ages. For example, comparing Nia aged 19 with Leo aged 28 orders Nia first;
selecting the minimum person keeps Nia's whole record, including her name.

Likewise, selecting the shortest word compares lengths but retains the
word itself. Equal lengths are a tie even when the words differ. An
exercise asking for a shortest word need not identify a unique answer.
If a task needs a particular tie rule, specify another comparison key.
See the [Comparator API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Comparator.html).

## Empty results and fallback policy

Empty input has no element to select, so the result is an empty Optional.
Choose the handling policy after the stream operation:

- `orElse(fallback)` returns the selected element or your fallback value.
- `ifPresent(action)` performs an action only when an element exists.
- `orElseThrow()` returns the element or throws if none exists.

`orElse(null)` is permitted, but accessing a field or calling an instance
method on that returned null reference will throw NullPointerException.
Use a presence-based action when absence is acceptable, or an explicit
exception when the application requires an element.

Numeric extremes used as fallbacks are ordinary values. They do not
participate in selection, and they can also be legitimate input values,
so the returned int alone cannot always distinguish absence. This is
different from supplying an identity to reduce.
See [Optional handling and complete examples](../foundations/optional.md#choose-how-to-handle-the-result)
and the [Optional API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Optional.html#orElse(T)).

## Connection to reduction

For non-null integers, `reduce(Integer::min)` selects a minimum and
`reduce(Integer::max)` selects a maximum. Both use the no-identity overload,
so empty input produces an empty Optional. A later `orElse(-1)` returns
minus one for that empty result; it does not make minus one a candidate
in the reduction. See [reduction without an identity](reduction.md#the-form-without-an-identity-returns-optional).

## Recap and self-check

1. Why does choosing the youngest person return Optional<Person>, rather
   than Optional<Integer>?
2. Do two different words of equal length tie under the length comparator?
3. What happens after `orElse(null)` if the next statement reads a field
   and the stream was empty?
4. Is a fallback passed to `orElse` also a reduction identity?

Practice: [MinMax.java](../../../src/main/java/com/amigoscode/_7_streams/_3_minmax/MinMax.java).
Checkpoint: [MinMax review](../../exercise-reviews/streams/min-max.md).
