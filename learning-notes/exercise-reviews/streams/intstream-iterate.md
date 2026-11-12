# IntStreamIterate review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [IntStreamIterate.java](../../../src/main/java/com/amigoscode/_7_streams/_2_intstreams/IntStreamIterate.java).
Concepts: [Iteration and limit placement](../../explanations/streams/iteration.md),
[primitive stream sources](../../explanations/streams/intstream.md#longstream-and-doublestream-sources).

## Review: 2026-10-27

All five TODOs were attempted. The source compiled and ran, printing 40
lines. All 35 deterministic lines matched the submitted pipelines, followed
by exactly five random values displayed with four fractional digits.
No exercise Java source was edited during this review.

| TODO | Verdict | Evidence |
|---|---|---|
| 1 | Correct | Ten even numbers: `0, 2, 4, ..., 18` |
| 2 | Correct | Fourteen multiples of seven: `7, 14, ..., 98`; uses the requested continuation predicate |
| 3 | Correct | Fibonacci values: `0, 1, 1, 2, 3, 5, 8, 13, 21, 34` |
| 4 | Correct end-exclusive range usage; endpoint wording ambiguous | `LongStream.range(1, 1_000_000).sum()` prints `499999500000`, summing one through 999,999 |
| 5 | Correct | Uses `DoubleStream.generate(Math::random).limit(5)` and prints exactly five values using `%.4f%n` |

## TODO 4: distinguish API behavior from ambiguous wording

The TODO explicitly names `LongStream.range`, but its phrase one to one
million can also be read as inclusive. The current code correctly uses
the named API. It does not include one million.

If the intended arithmetic task includes one million, use this fragment
inside main:

```java
long result4 = LongStream.range(1, 1_000_001).sum();
```

That retains the named factory and returns `500000500000`.
`LongStream.rangeClosed(1, 1_000_000).sum()` gives the same inclusive sum.
This is an endpoint interpretation to resolve, not evidence that the
learner misused LongStream. The source was left unchanged.

## Question from TODO 3: limit before or after mapToInt?

Both placements are acceptable and produce the same ten values here.
The projection takes one array state and returns one int, preserving
the sequence order. Both pipelines remain lazy and can terminate.

The [main explanation](../../explanations/streams/iteration.md#moving-limit-across-this-maptoint)
includes a complete comparison, the Fibonacci state transition, and a
counterexample showing why limit and filter cannot generally be reordered.
The learner's current placement requires no correction.

## Validation and understanding checkpoint

The deterministic output was checked exactly: ten even values, fourteen
multiples, ten Fibonacci values, and the end-exclusive long sum. Random
output was checked for count and four-decimal formatting, allowing rounded
`1.0000`; a small sample does not prove a generator's mathematical bounds.
The `Math::random` source and format were also checked by inspection.

A separate temporary Java program verified identical Fibonacci lists for
both limit placements, both inclusive alternatives for the long sum, and
the filter-order counterexample. The later complete explanation examples
were also run against their documented output.

The code demonstrates correct sequence generation, state-pair projection,
long arithmetic, and supplier-based double generation. The learner asked
about limit placement; explain the one-to-one projection and compare it
with filtering before treating that distinction as understood.

Next checkpoint: identify whether the arithmetic target includes one
million, and explain the endpoint actually emitted by each range factory.
There is no other algorithmic correction required in this submission.

## Follow-up review: 2026-10-31

All five TODOs are now correct and acceptable. TODO 4 uses
`LongStream.range(1, 1_000_001).sum()`, including one million while retaining
the requested end-exclusive factory. It prints `500000500000`.

The current source compiled and ran successfully. All 35 deterministic
output lines matched expectations, followed by five random values printed
with four fractional digits. TODOs 1, 2, 3, and 5 remain correct. No Java
source was edited during this review.

The learner reported completing the endpoint correction, and the current
source and output verify it. An explanation of endpoint semantics and why
the Fibonacci projection permits either limit placement remains an
understanding checkpoint; this review does not establish that explanation.

Next proposed step: introduce `peek()` and `findFirst()`, including their
input/result contracts and a complete example, before attempting TODOs 1–2
in [LazyStreams.java](../../../src/main/java/com/amigoscode/_7_streams/_11_understanding/LazyStreams.java).
Keep exercise implementations as the learner's work.
