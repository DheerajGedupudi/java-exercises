# Mistake patterns

[Home](../README.md) · [Protocol](../protocol.md) · [Self-check](self-check.md)

Mistakes that have shown up more than once across exercises, built from the
exercise reviews. Use the checklist before asking for a review. Every
review updates this file (see the [protocol](../protocol.md)).

## Checklist before asking for a review

1. **Shown?** Every TODO that says "print" has a `println`. Every lambda you
   defined has its method called (`get`, `accept`, `apply`, `test`, `call`).
2. **Exact?** Variable names, literal text, and the named API match the TODO
   word for word.
3. **Endpoints?** Write down the first and last value you expect. Is the
   upper bound included?
4. **Wording?** Does each condition test what the sentence says, for example
   "contains" versus "starts with"?
5. **Empty?** What happens if the input is empty? Use a deliberate Optional
   policy, not a sentinel or `null`.

## Summary

| # | Pattern | Times | Last seen | Status |
|---|---|---|---|---|
| 1 | [Defined or computed, but never shown](#1-defined-or-computed-but-never-shown) | 6 | 2026-10-26 | Not seen in the four exercises since |
| 2 | [Requested name, text, or API not followed exactly](#2-requested-name-text-or-api-not-followed-exactly) | 6 | 2026-10-27 | Not seen in the three exercises since |
| 3 | [Range endpoint off by one](#3-range-endpoint-off-by-one) | 2 | 2026-10-27 | Both fixed |
| 4 | [Reduce: partial result versus element](#4-reduce-partial-result-versus-element) | 2 | 2026-10-27 | Fixed; comes back in ReduceExercise |
| 5 | [Sentinel or null instead of an Optional policy](#5-sentinel-or-null-instead-of-an-optional-policy) | 2 | 2026-10-31 | `null` case fixed on 2026-11-11 |

## 1. Defined or computed, but never shown

**What it looks like:** a lambda is defined but its method is never called,
or a value is returned or a collection is built but never printed.

**Why it happens:** a lambda is only stored behavior until something calls
its method. Here the console is the result. On LeetCode the judge reads
your return value, so the habit of printing may not be there.

**How to catch it:** for each TODO that says "print", point to its
`println`. For each lambda, point to the line that calls its method.

| Exercise | TODO | Date | What was missing |
|---|---|---|---|
| [LambdaExpressions](../exercise-reviews/functional-interfaces/lambda-expressions.md) | 3 | 2026-10-23 | `call()` result discarded, not printed |
| [Supplier](../exercise-reviews/functional-interfaces/supplier.md) | 1 | 2026-10-25 | Supplier defined but `get()` never called |
| [Consumer](../exercise-reviews/functional-interfaces/consumer.md) | 7 | 2026-10-25 | Map filled but not printed |
| [Predicate](../exercise-reviews/functional-interfaces/predicate.md) | 6 | 2026-10-25 | Filtered list built but not printed |
| [BiFunction](../exercise-reviews/functional-interfaces/bifunction.md) | 1–3 | 2026-10-25 | Three functions defined, none applied |
| [ForEachAndCollect](../exercise-reviews/streams/foreach-and-collect.md) | 6 | 2026-10-26 | Class name not printed |

Concepts: [lambdas](../explanations/foundations/lambdas.md),
[Supplier](../explanations/functional-interfaces/supplier.md).

## 2. Requested name, text, or API not followed exactly

**What it looks like:** the code works, but a variable name, an output
string, or the API the TODO names is different from what was asked.

**Why it happens:** once the logic works, the rest feels cosmetic. But the
TODO names an API because it wants you to practise that API, and in an
interview or code review the spec is the spec.

**How to catch it:** after writing a TODO, reread it once and check every
quoted name, method name, and exact string against your code.

| Exercise | TODO | Date | Asked for | Written |
|---|---|---|---|---|
| [FunctionalInterfaceBasics](../exercise-reviews/functional-interfaces/functional-interface-basics.md) | 6 | Oct 2026 | `Hey there, Bob!` | A different greeting |
| [LambdaExpressions](../exercise-reviews/functional-interfaces/lambda-expressions.md) | 3 | 2026-10-23 | `Callable` | `callable` |
| [LambdaExpressions](../exercise-reviews/functional-interfaces/lambda-expressions.md) | 4 | 2026-10-23 | `sumShort` | `sumVerbose` |
| [Function](../exercise-reviews/functional-interfaces/function.md) | 5 | 2026-10-25 | `intToWord` | `inToWord` |
| [FilterBasics](../exercise-reviews/streams/filter-basics.md) | 6 | 2026-10-26 | `Collectors.toList()` | `Stream.toList()` |
| [ReduceBasics](../exercise-reviews/streams/reduce-basics.md) | 3 | 2026-10-27 | `reduce` without an identity | `reduce` with an identity |

Three of these are still open. They are the
[loose ends](../learning-plan.md#loose-ends) in the roadmap.

## 3. Range endpoint off by one

**What it looks like:** a range stops one short of the value the task
includes.

**Why it happens:** `range(a, b)` and `Math.random()` exclude their upper
end, while `rangeClosed(a, b)` includes it. "From 1 to 1,000,000" in a TODO
usually includes 1,000,000.

**How to catch it:** write down the first and last value you expect, then
check both against the API's contract. A small random sample can't prove a
bound; reason from the expression.

| Exercise | TODO | Date | What happened |
|---|---|---|---|
| [CreatingStreams](../exercise-reviews/streams/creating-streams.md) | 6 | 2026-10-25 | `Math.random() * 100` gives 0–99, not 0–100 |
| [IntStreamIterate](../exercise-reviews/streams/intstream-iterate.md) | 4 | 2026-10-27 | `range(1, 1_000_000)` leaves out 1,000,000 |

Concept: [ranges](../explanations/streams/intstream.md#ranges-and-array-sources).

## 4. Reduce: partial result versus element

**What it looks like:** the accumulator treats its first parameter (the
running result) like an element, or the identity isn't neutral.

**Why it happens:** in a loop, `sum += x * x` keeps the running total and
the element in clearly different variables. In `(a, b) -> ...` both look
alike.

**How to catch it:** name the parameters `(sum, n)` or `(acc, x)`. Check
the identity: combining it with any value must give that same value back.

| Exercise | TODO | Date | What happened |
|---|---|---|---|
| [ReduceBasics](../exercise-reviews/streams/reduce-basics.md) | 3 | 2026-10-27 | `""` identity with a space separator gave a leading space |
| [ReduceBasics](../exercise-reviews/streams/reduce-basics.md) | 6 | 2026-10-27 | Squared the running total again each step, which overflowed `int` |

Concepts: [two reduce forms](../explanations/streams/reduction.md#two-overloads-two-result-contracts),
[sum of squares](../explanations/streams/reduction.md#sum-of-squares-transform-each-element-then-combine).

## 5. Sentinel or null instead of an Optional policy

**What it looks like:** an empty result becomes `Integer.MIN_VALUE`, `-1`,
or `null` without a deliberate choice.

**Why it happens:** sentinels like `Integer.MAX_VALUE` are standard in
LeetCode min/max scans. In stream code, `Optional` says "maybe nothing"
explicitly, and you decide what absence means.

**How to catch it:** when a terminal operation returns an Optional, pick
one of these:
- `orElse(value)` when there's a real default;
- `ifPresent` for an action;
- `orElseThrow` when absence is an error.

Avoid `orElse(null)`.

| Exercise | TODO | Date | What happened |
|---|---|---|---|
| [MinMax](../exercise-reviews/streams/min-max.md) | 1, 2, 5 | 2026-10-31 | Extreme-value fallbacks: valid here, but they would hide an empty input |
| [MinMax](../exercise-reviews/streams/min-max.md) | 4 | 2026-10-31 | `orElse(null)` and then field access would throw on an empty list; fixed 2026-11-11 |

Concepts: [fallback policy](../explanations/streams/min-max.md#empty-results-and-fallback-policy),
[Optional](../explanations/foundations/optional.md).

## Watch list: seen once

| Mistake | Exercise | Date | Note |
|---|---|---|---|
| Condition didn't match the wording | [FilterBasics](../exercise-reviews/streams/filter-basics.md) TODO 4 | 2026-10-26 | `startsWith("a")` instead of `contains("a")`. Test with a value that tells the two apart. See [matching the wording](../explanations/streams/filtering.md#match-the-condition-to-the-wording). |
| Lambda parameter reused a local variable's name | [MethodReferences](../exercise-reviews/functional-interfaces/method-references.md) TODO 6 | 2026-10-23 | Compile error. See [parameter scope](../explanations/foundations/lambdas.md#parameter-scope). |
| Same values versus the same stream | [IntStreamBasics](../exercise-reviews/streams/intstream-basics.md) TODO 6 | 2026-10-27 | Two factory calls make two streams. LazyStreams TODO 3 comes back to this. See [separate streams](../explanations/streams/intstream.md#same-values-separate-streams). |

A watch-list item moves up into the patterns when it happens a second time.
