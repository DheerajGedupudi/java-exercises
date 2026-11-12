# LambdaExpressions review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [LambdaExpressions.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_1_gettingstarted/LambdaExpressions.java).

## Initial review: 2026-10-23

The file compiled and ran. TODOs 1, 2, 5, 6, and 7 met their requirements.
Two TODOs needed attention:

| TODO | Finding |
|---|---|
| 3 | The `Callable` returned a string, but `myCallable.call()` discarded it. The result needed printing. The literal also used lowercase `callable` instead of the requested `Callable`. |
| 4 | The single-expression addition was correct, but its variable was still named `sumVerbose`; the prompt requested `sumShort`. |

The initial output omitted the callable result:

```text
Sorted by length: [Al, Bob, Charlie, Alexandra]
Running in a lambda!
Verbose sum: 7
Concatenated String: Hello + World
Hello!
Goodbye!
JOHN
```

## Follow-up

The callable result was printed and `sumShort` was correctly renamed and
used. TODO 7 was also shortened to an expression lambda, still printing `JOHN`.
Parentheses around its single inferred parameter were optional and valid.

At the last recorded check, the only remaining prompt mismatch was the
capitalization of `Callable` in the returned string. No later check of that
literal is recorded here.

## Lesson to carry forward

Returning a value and printing it are separate actions. Defining a lambda
and calling its functional method are separate actions too. IntelliJ's
method-reference suggestion was an optional simplification of valid code.

Read [lambdas](../../explanations/foundations/lambdas.md),
[BiFunction](../../explanations/functional-interfaces/bifunction.md), and
[method references](../../explanations/foundations/method-references.md).

