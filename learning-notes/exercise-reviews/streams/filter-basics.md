# FilterBasics review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [FilterBasics.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/FilterBasics.java).

## Review: 2026-10-26

The source compiled and ran. It printed 18 lines; the other TODOs' results
matched expectations, but TODO 4's expected two lines were absent.

| TODO | Review result |
|---|---|
| 1 | Correct: kept `stream`, `lambda`, `collection`, `filter`, and `reduce` |
| 2 | Correct: printed `2`, `4`, `6`, `8`, `10` |
| 3 | Correct: printed `Amsterdam`, `Athens`, and `Auckland` |
| 4 | Incorrect second condition: used `startsWith("a")` instead of testing whether the word contains `"a"` |
| 5 | Correct: removed nulls with a lambda and printed `Hello`, `World`, `Java`, `Streams` |
| 6 | Correct values `[6, 7, 8, 9, 10]`, but the prompt specifically requests `Collectors.toList()` |

## TODO 4: prefix versus containment

The first condition, length greater than three, is correct. The second
condition checks a prefix. No word in the supplied list passes both of
the submitted conditions, so the pipeline prints nothing.

The required second condition is:

```java
.filter(s -> s.contains("a"))
```

This is a pipeline fragment. With the existing first filter and terminal
operation, the expected TODO 4 output is:

```text
stream
lambda
```

The stream chaining is valid; the string method chosen for the predicate
does not match the requested condition.

## TODO 6: behavior versus the requested API practice

`Stream.toList()` correctly produced the requested values in this project.
There is no list-content error. To follow this TODO's explicit instruction,
use the terminal operation:

```java
.collect(Collectors.toList())
```

Keep the existing separate print of the collected list. The required
`Collectors` import is already present. The null-check lambda in TODO 5 is
also valid; replacing it with `Objects::nonNull` is optional.

## Follow-up: complete

The second condition in TODO 4 now uses `contains("a")`, and TODO 6 uses
`collect(Collectors.toList())` as requested. The source compiled and ran,
with all 20 output lines matching expectations. TODO 4 printed `stream`
and `lambda`, and TODO 6 printed `[6, 7, 8, 9, 10]`.

All six TODOs now meet their requirements. The next stream exercise is
MapBasics, after reading the [mapping refresher](../../explanations/streams/mapping.md).

## Lesson to carry forward

Match the predicate to the wording, then predict which supplied values
should pass. Include a value with the letter inside the word to distinguish
containment from a prefix check. A successful run does not establish that
the condition matches the exercise.

Read [filtering](../../explanations/streams/filtering.md#match-the-condition-to-the-wording)
and [list accumulation](../../explanations/streams/overview.md#collecting-into-a-list).
No Java source was edited during the review.

