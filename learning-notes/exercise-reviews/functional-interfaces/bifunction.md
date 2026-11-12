# BiFunctionExercise review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [BiFunctionExercise.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/BiFunctionExercise.java).

## Review: 2026-10-25

| TODO | Result at the review |
|---|---|
| 1: join two strings | Correct anonymous-class implementation on inspection |
| 2: maximum | Correct anonymous-class implementation using `Math.max` on inspection |
| 3: repeat a string | Correct block lambda using `String.repeat` on inspection |
| 4: composition | Partial: the length function was defined, but composition, invocation, and output were absent |
| 5: pair names and ages | Not implemented |

The source compiled and ran without output. It defined the first three
behaviors but did not invoke them. Their logic was reviewed from the source;
that run did not exercise their returned values.

The anonymous classes were valid. Converting them to lambdas was optional.
The explicit `return` in the third lambda's block was also valid.

## Understanding the unfinished step

The requested flow is two inputs, a combined string, then its integer length.
Composition starts from the combiner and adds the length function afterward.
The composition itself is another `BiFunction`; calling `apply` obtains a result.

The partial `lengthOf.andThen()` line was commented out. It did not cause a
compilation error, but it did not implement the requested flow.

## Learning priority

An earlier recommendation parked this file to reach streams sooner. Once
the user reported incomplete clarity on the core interfaces, the plan changed:
finish this work after Supplier, Consumer, and Function. Completed code alone
is not the criterion for moving on.

Read [BiFunction and composition](../../explanations/functional-interfaces/bifunction.md)
and [IntStream ranges](../../explanations/streams/intstream.md#ranges-and-array-sources)
before the pairing TODO.

