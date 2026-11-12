# PredicateExercise review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [PredicateExercise.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/PredicateExercise.java).

## Initial review: 2026-10-25

The source compiled and ran. TODOs 1–5 and 7 were correct; all 13 printed
boolean results matched expectations:

| Check | Inputs | Results |
|---|---|---|
| Positive and even | `4`, `-2`, `3`, `0` | `true`, `false`, `false`, `false` |
| Even or divisible by three | `4`, `9`, `7`, `6` | `true`, `true`, `false`, `true` |
| Not positive | `5`, `-3`, `0` | `false`, `true`, `true` |
| String length matches | `("hello", 5)`, `("hi", 3)` | `true`, `false` |

The combined conditions were in a different order from the wording, which
was valid for these pure conditions on the supplied non-null integers.
The null-check-first `isNotEmpty` implementation was correct on inspection;
it was not invoked in that program. Manual checks with null, an empty string,
and a nonempty string would give false, false, and true.

TODO 6 correctly collected the positive even values. Its only missing
requirement was printing the resulting list.

## Follow-up: complete

After adding the print, the source ran successfully and all 14 output lines
matched expectations. The added list output was `[6, 8]`. All seven TODOs
met their requirements.

## Lesson to carry forward

`and`, `or`, and `negate` build predicates; `test` evaluates one for an input.
Filtering receives the predicate itself and keeps values answering true.
The collected result still needs a print statement when the prompt asks
for console output.

Read [Predicate](../../explanations/functional-interfaces/predicate.md)
and [pipeline execution](../../explanations/streams/overview.md).

