# MinMax review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [MinMax.java](../../../src/main/java/com/amigoscode/_7_streams/_3_minmax/MinMax.java).
Concepts: [Minimum, maximum, comparators, and fallback policies](../../explanations/streams/min-max.md),
[Optional](../../explanations/foundations/optional.md),
[reduction](../../explanations/streams/reduction.md).

## Review: 2026-10-31

All six TODOs are correct for the supplied data. The current source compiled
and ran successfully, and all six output lines matched expectations:

```text
3
91
Hi
Diana 22
91
-1
```

| TODO | Verdict | Evidence |
|---|---|---|
| 1 | Correct | Uses min with natural ordering; prints 3 |
| 2 | Correct | Uses max with natural ordering; prints 91 |
| 3 | Correct | Uses the requested length comparator; prints Hi, one of the two shortest words |
| 4 | Correct for the supplied nonempty list | Uses the requested age comparator; prints Diana 22 |
| 5 | Correct | Uses the requested no-identity reduction with Integer::max; prints 91 |
| 6 | Correct | Reduces the empty source with Integer::min and applies orElse(-1); prints -1 |

TODO 6 asks for an empty minimum result and the fallback, without explicitly
requiring the min method. The submitted reduction satisfies those requirements.
It does not need replacing just to use a different valid API.

## Robustness and optional cleanup

- TODO 4 extracts with `orElse(null)` and immediately reads fields on the
  result. If people were empty, that field access would throw
  NullPointerException. This does not fail the provided nonempty input.
  Choose an Optional action when no person is acceptable, or an explicit
  exception when a person is required. The main chapter explains the
  [fallback policy](../../explanations/streams/min-max.md#empty-results-and-fallback-policy).
- Direct access to `result4.name` and `result4.age` is legal from this
  enclosing class, as the successful compilation confirms. The record's
  `name()` and `age()` accessors are the usual style for reading its components.
- The numeric extreme fallbacks in TODOs 1, 2, and 5 are valid and unused
  for this data. They are fallback choices, not selection identities, and
  can conceal absence if the same extreme is also a legitimate result.
- Both Hi and Go have length two. The run returned Hi; the task does not
  require a particular choice among equally short words.
- The Optional import is unused and may be removed as optional cleanup.

## Validation and understanding checkpoint

Validation ran this self-contained source using Java's source-file launcher.
It verifies the supplied inputs and the actual empty numeric source in
TODO 6, rather than the entire Maven project. The empty-people failure is
an inspection-based finding; no changed-input variant was executed.
No exercise Java source was edited.

The code demonstrates use of natural ordering, extracted comparison keys,
no-identity reductions, and the empty numeric fallback. The learner has
not yet explained the empty-person case or the distinction between a
comparison key and the selected element.

Next understanding checkpoint: predict what TODO 4 does if people is empty,
then explain which Optional policy fits printing a person only when present.
No correction is required to pass the supplied exercise data.

## Review: 2026-11-11 — TODO 4 revision

After the previous review, TODO 4 was changed to keep the
`Optional<Person>` returned by `min`, check `isPresent()`, and read the
record through its `name()` and `age()` accessors.

| TODO | Verdict | Evidence |
|---|---|---|
| 4 | Correct, including an empty people list | Supplied data still prints Diana 22; an empty-list variant prints nothing and does not throw |

What was run:

- The current source, using Java 25's source-file launcher. All six lines
  were unchanged: 3, 91, Hi, Diana 22, 91, -1.
- A scratch copy with `people` replaced by an empty list. TODO 4 printed
  nothing, the other five lines were unchanged, and no exception occurred.
  This was a temporary copy outside the repository; the exercise source
  was not edited.

Earlier findings now resolved:

- The NullPointerException risk from `orElse(null)` followed by field
  access. The Optional is now checked before its value is used.
- Direct field access replaced by the record accessors.
- The `Optional` import is now used.

The revision applies a *skip when absent* policy, which fits a task that
only prints a youngest person when one exists. If a person were required,
`orElseThrow` would state that instead.

Optional style, not required: `isPresent()` followed by `get()` is correct.
`ifPresent` expresses the same policy in one call and removes the separate
check-then-get. See
[acting instead of returning a value](../../explanations/foundations/optional.md#if-you-want-an-action-rather-than-a-returned-value)
and [what about get](../../explanations/foundations/optional.md#what-about-get).

The earlier notes about numeric-extreme fallbacks in TODOs 1, 2, and 5 and
the tie between Hi and Go still apply unchanged.

Understanding checkpoint: the code shows the skip-when-absent policy, but
the learner has not yet explained it in words. Questions still to answer:

1. With an empty people list, what did the original `orElse(null)` version
   do, and what does the current version do?
2. When would `orElseThrow` be a better choice than skipping?
3. `comparingInt(Person::age)` compares ages. What type does `min` return,
   and why is it not an `int`?
