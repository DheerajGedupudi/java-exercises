# ReduceBasics review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [ReduceBasics.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/ReduceBasics.java).
Concepts and alternatives: [Reduction](../../explanations/streams/reduction.md).

## Review: 2026-10-27

The submitted source compiled and ran. TODOs 1, 2, 4, and 5 are correct.
TODOs 3 and 6 need changes. No exercise Java source was edited during review.

| TODO | Verdict | Evidence and feedback |
|---|---|---|
| 1 | Correct and acceptable | Identity zero with addition prints `55`; `Integer::sum` is an optional equivalent |
| 2 | Correct and acceptable | Identity one with multiplication prints `120` |
| 3 | Needs correction | Uses the two-argument overload instead of the requested no-identity overload and prints an extra leading space; `""` is not neutral for this separator rule |
| 4 | Correct and acceptable | Prints `55`; matches the exercise's identity-based sum requirement |
| 5 | Correct and acceptable | Prints `Awesome`; both `Streams` and `Awesome` have length seven, and the strict `>` comparison chooses the right-hand value on ties |
| 6 | Incorrect | Prints `-887005403` instead of `385`; squares the accumulated result again at every step, eventually overflowing int |

The exact six output lines were:

```text
55
120
 Java Streams Are Awesome
55
Awesome
-887005403
```

The third line begins with a space. The numeric overflow in the last line
is a consequence of an incorrect recurrence, not the underlying problem.

## Corrections to make

For TODO 3, use the requested overload and extract the contained value:

```java
Optional<String> result3 = words.stream().reduce((a, b) -> a + " " + b);
System.out.println(result3.orElse(""));
```

`Optional` is already imported in the exercise. The main chapter explains
[the no-identity form and joining](../../explanations/streams/reduction.md#the-form-without-an-identity-returns-optional).

For TODO 6, square each input once, then combine by addition:

```java
int result6 = numbers.stream()
        .map(n -> n * n)
        .reduce(0, (a, b) -> a + b);
System.out.println(result6);
```

This retains the requested use of `reduce` with identity zero. Do not use
`(a, b) -> a + b * b` with this two-argument overload: it works in a
sequential walkthrough but violates the reduction contract. See the
[sum-of-squares explanation](../../explanations/streams/reduction.md#sum-of-squares-transform-each-element-then-combine).

## Optional improvements and validation

The existing addition, multiplication, and longest-word lambdas are valid;
they do not need rewriting simply because another API exists. Alternatives
using `Integer::sum`, primitive-stream `sum()`, `Collectors.joining`, and
`max(Comparator.comparingInt(...))` are explained in the
[main chapter](../../explanations/streams/reduction.md#alternatives-after-practising-reduce).
Those specialized terminals would not fulfil TODOs that explicitly require
`reduce`. The `max` alternative can choose a different word when lengths tie.

Suggested corrections and alternatives were executed separately in a
temporary Java program. Joining, addition, and square-sum results matched
expectations; empty and singleton joining/square-sum cases passed. The
mapped sum of squares also returned `385` with a parallel stream. For
longest-word alternatives, validation accepted either seven-character
maximum rather than requiring the same tie choice.

The learner's code demonstrates the correct addition/product identities
and a valid longest-word comparison. Understanding of Optional results,
neutral values for separator rules, and the accumulated value versus an
original input needs a follow-up explanation; successful output alone
would not establish that understanding.

Next step: correct TODOs 3 and 6 and request a follow-up review before moving
to another exercise. This checkpoint does not verify later source changes.

## Follow-up review: 2026-10-27

All six TODOs are now correct and acceptable. The corrected source compiled
and ran, and all six output lines matched exactly:

```text
55
120
Java Streams Are Awesome
55
Awesome
385
```

- TODO 3 now calls the requested no-identity overload, stores an
  `Optional<String>`, and prints its contained string using `orElse("")`.
  There is no extra leading space.
- TODO 6 maps each original number to its square and then reduces those
  squares by addition with identity zero. Both the output and the
  identity/associativity requirements are correct.
- TODOs 1, 2, 4, and 5 remain correct. The previously recorded tie choice
  for the longest word remains acceptable.

The learner reported that the no-identity overload was unfamiliar. The
corrected code uses it correctly; that does not yet verify an explanation
of empty or singleton inputs. The main chapter now includes a
[comparison of the two overloads](../../explanations/streams/reduction.md#two-overloads-two-result-contracts),
including their different return types and the first-element starting point.

No Java source was edited during this review. Optional method-reference
rewrites remain optional; there is no required cleanup before moving on.
For revision, predict what the two addition forms return for an empty
stream and for a stream containing only seven. Then continue with the
[IntStream chapter](../../explanations/streams/intstream.md) in the learning plan.
