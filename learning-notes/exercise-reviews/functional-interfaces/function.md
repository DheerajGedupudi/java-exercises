# FunctionExercise review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [FunctionExercise.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/FunctionExercise.java).

## Review: 2026-10-25

The source compiled and ran. All six printed lines matched expectations:

```text
5
HELLO
5
5
[5, 6, 6, 4]
22
```

| TODO | Review result |
|---|---|
| 1 | Correct string-length function and invocation |
| 2 | Correct uppercase function and invocation |
| 3 | Correct `toUpperCase.andThen(stringLength)` |
| 4 | Correct `stringLength.compose(toUpperCase)` with the same operation order |
| 5 | Correct numeric mapping logic; variable is named `inToWord` but the prompt requests `intToWord` |
| 6 | Correct mapping to `[5, 6, 6, 4]`; using `Stream.toList()` is valid for this task |
| 7 | Correct generic-helper call, returning `22` for `Functional Programming` |

The remaining prompt requirement at this checkpoint is the variable-name
correction in TODO 5. The long `if/else` lambda is valid, though formatting
or an alternative representation would make it easier to read.

## Additional checks

TODO 5 is defined but not invoked by the original `main`. An isolated
temporary program exercised that exact declaration for `-1`, `0`, `1`, `2`,
`3`, `4`, `5`, `6`, and `42`. All results matched: one through five map to
their words, and the other inputs return `unknown`.

The same nine cases were run against the switch-expression and map-lookup
alternatives documented in the [Function chapter](../../explanations/functional-interfaces/function.md#alternatives-for-a-fixed-number-to-word-mapping).
All three implementations agreed. The exercise source was not edited.

## Lesson to carry forward

The two composition expressions in TODOs 3 and 4 both run uppercase conversion
before measuring length. In contrast, `Consumer.andThen` passes the same
original input to two actions rather than forwarding a computed result.

The returned list from `Stream.toList()` is unmodifiable. That is fine here,
because the exercise prints the list without modifying it. See the
[list accumulation note](../../explanations/streams/overview.md#collecting-into-a-list).

Next: correct the requested variable name, then finish the remaining
[BiFunction exercises](bifunction.md) after reviewing their composition flow.

