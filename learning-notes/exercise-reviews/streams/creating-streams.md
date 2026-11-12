# CreatingStreams review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [CreatingStreams.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/CreatingStreams.java).

## Review: 2026-10-25

The source compiled and ran. TODOs 1–5 were correct, and all 19 lines matched
the expected output:

```text
Alice
Bob
Charlie
Diana
Eve
Apple
Banana
Cherry
Date
Elderberry
Red
Green
Blue
0
H
e
l
l
o
```

| TODO | Result |
|---|---|
| 1 | Correct list source and printing |
| 2 | Correct array source and printing |
| 3 | Correct source from individual values |
| 4 | Correct empty-stream count of zero |
| 5 | Correct character conversion from `IntStream` to `Stream<Character>` |
| 6 | Not implemented at this checkpoint |

The source comment identified an unmet learning need: `generate` and `limit`
had not been explained before the exercise expected their use. A refresher
was then given using a constant supplier and a finite limit.

## Follow-up: generated source and range boundary

TODO 6 is now implemented. The source compiled and ran, with the first
19 output lines still matching TODOs 1–5 and exactly ten integer lines
produced by the generated pipeline.

The use of `Stream.generate`, the no-argument supplier, `limit(10)`, and
terminal `forEach` is correct. The multiplier in
`(int) (Math.random() * 100)` makes the possible integer range zero through
99. It cannot produce 100.

The chapter previously chose zero through 100 inclusive for this exercise.
Under that interpretation, change the multiplier to 101:

```java
(int) (Math.random() * 101)
```

The range conclusion follows from the expression, not from whether a small
random sample happens to contain the endpoint. The run verified count,
integer output, and the current zero-through-99 range. The Java source was
not changed during the review.

## Follow-up: range corrected and exercise complete

The multiplier is now 101, giving the chosen inclusive zero-through-100
range. The source compiled and ran successfully. The first 19 lines still
matched the earlier results, followed by exactly ten integer values, each
within the required interval. The expression's bounds establish endpoint
coverage; the run checks count and valid values.

All six TODOs now meet their requirements. The next stream exercise is
FilterBasics, with its [filtering refresher](../../explanations/streams/filtering.md).

## Learning decision at the initial checkpoint

The user reported incomplete clarity on Supplier and Consumer. The agreed
plan now strengthens the core functional interfaces before returning to
TODO 6. The earlier correct code remains valid; output checks alone were
not enough evidence to advance the learning sequence.

Read [creating and generating streams](../../explanations/streams/creating-streams.md),
[Supplier](../../explanations/functional-interfaces/supplier.md), and
[IntStream](../../explanations/streams/intstream.md) when returning to this exercise.

