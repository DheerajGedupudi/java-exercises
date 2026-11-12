# IntStreamBasics review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [IntStreamBasics.java](../../../src/main/java/com/amigoscode/_7_streams/_2_intstreams/IntStreamBasics.java).
Concepts: [IntStream](../../explanations/streams/intstream.md).

## Review: 2026-10-27

The source compiled and ran. TODOs 1–5 are correct and acceptable; all 18
printed lines matched the expected values. TODO 6 is still unimplemented.
No exercise Java source was edited during this review.

| TODO | Verdict | Evidence and optional improvement |
|---|---|---|
| 1 | Correct | `range(1, 11)` prints one through ten |
| 2 | Correct | Addition reduction with identity zero prints `55`; `sum()` would express the same task more directly |
| 3 | Correct | `boxed().toList()` prints `[1, 2, 3, 4, 5]`; no specific collector is required by this TODO |
| 4 | Correct | `of(...)` prints `10`, `20`, `30`, `40`, `50` |
| 5 | Correct | `average().orElse(0)` prints `86.6`; `orElse(0.0)` is an optional readability improvement |
| 6 | Incomplete | The learner left a comment saying they could not think of a solution without reusing the stream; no minimum or maximum is calculated or printed |

## Finish TODO 6

Each `IntStream.of(...)` call creates a separate stream. The exercise allows
creating the stream twice; using identical source values does not mean the
same stream instance is reused.

This is a valid completion to write inside the exercise's main method:

```java
int minimum = IntStream.of(15, 3, 42, 7, 28).min().orElse(0);
int maximum = IntStream.of(15, 3, 42, 7, 28).max().orElse(0);
System.out.println(minimum);
System.out.println(maximum);
```

Expected added output:

```text
3
42
```

Both sources are nonempty, so the fallback is unused. The first stream is
consumed by `min`; the second is consumed by `max`. The main chapter explains
[the separate lifetimes](../../explanations/streams/intstream.md#same-values-separate-streams).
The existing complete numeric-results example uses this same approach with
different data. A one-pass summary-statistics solution is another option
mentioned in the exercise; it is not required to finish this TODO.

## Optional cleanup

The TODO 2 reduction is valid. A more direct equivalent is:

```java
int result2 = IntStream.rangeClosed(1, 10).sum();
```

The TODO 5 fallback literal zero is also valid; Java converts it to double.
The [main chapter](../../explanations/streams/intstream.md#sum-and-fallback-syntax-valid-versus-more-direct)
explains both readability suggestions. Neither blocks completion.

## Understanding checkpoint and next step

The submission correctly uses ranges, boxing, reduction, and an optional
average result. The learner's comment specifically identifies confusion
about source values versus stream instances. Correct output does not yet
verify an explanation of those lifetimes.

Finish TODO 6 and request another review. Explain why two factory calls
work, while two terminal calls on the same stream variable do not.
The suggested TODO 6 completion was not inserted into or executed as part
of the submitted source; this checkpoint verifies only its current TODOs
1–5 and the absence of TODO 6 output.

## Follow-up review: 2026-10-27

All six TODOs are now correct and acceptable. The submitted source compiled
and ran, and all 19 output lines matched expectations. The previous 18
values remain correct, and the final line is:

```text
3 42
```

TODO 6 creates two separate IntStreams, computes the minimum and maximum,
and prints both. Printing the values together on one line fulfils the task.
TODO 2 now uses the more direct `sum()` terminal. The TODO 5 `orElse(0)`
remains valid; changing the literal to `0.0` is optional.

The code now correctly distinguishes separate factory calls from reusing
a consumed stream. An explanation in the learner's own words remains a
useful understanding checkpoint. No exercise Java source was edited during
this review.

Next: read [generating sequences with iterate](../../explanations/streams/iteration.md),
then attempt TODOs 1–2 in
[IntStreamIterate.java](../../../src/main/java/com/amigoscode/_7_streams/_2_intstreams/IntStreamIterate.java).
