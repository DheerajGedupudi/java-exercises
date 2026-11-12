# Learning plan

[Home](README.md) · [Current progress](progress.md)

## Goal and starting point

Become comfortable choosing, writing, and explaining Java stream pipelines.
You have extensive Java algorithm practice, including more than 1,300 LeetCode
problems. The refresher focuses on functional interfaces and stream APIs.

Correct output establishes what an attempt did for the checked cases.
Understanding also requires explaining the contract, when the code runs,
and why that interface or operation fits the task.

## Phase 1: make the core interfaces clear

| Order | Read | Attempt |
|---|---|---|
| 1 | [Supplier](explanations/functional-interfaces/supplier.md) | [SupplierExercise](../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/SupplierExercise.java) |
| 2 | [Consumer](explanations/functional-interfaces/consumer.md) | [ConsumerExercise](../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/ConsumerExercise.java) |
| 3 | [Function](explanations/functional-interfaces/function.md) | [FunctionExercise](../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/FunctionExercise.java) |
| 4 | [BiFunction](explanations/functional-interfaces/bifunction.md) | [BiFunctionExercise](../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/BiFunctionExercise.java) |
| 5 | [Predicate](explanations/functional-interfaces/predicate.md) | Explain and revise the [completed exercise](../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/PredicateExercise.java) |

Use a short lesson and a few TODOs at a time. Before a new API, cover its
purpose, argument types, result, and one example. Then predict output,
write your own attempt, run it, and explain what happened.

Supplier and Consumer have now passed review, including their output
corrections. Use [progress](progress.md) for subsequent updates.

The completed foundation exercises remain useful. Revisit
[functional interfaces](explanations/foundations/functional-interfaces.md),
[lambdas](explanations/foundations/lambdas.md),
[anonymous classes](explanations/foundations/anonymous-classes.md), or
[method references](explanations/foundations/method-references.md) when a gap appears.

## Phase 2: resume the stream basics

1. Read the [pipeline overview](explanations/streams/overview.md).
2. Finish [CreatingStreams](../src/main/java/com/amigoscode/_7_streams/_1_basics/CreatingStreams.java),
   using the [creation and generation chapter](explanations/streams/creating-streams.md).
3. Read the [filtering refresher](explanations/streams/filtering.md), then attempt
   [FilterBasics](../src/main/java/com/amigoscode/_7_streams/_1_basics/FilterBasics.java).
4. Read the [mapping refresher](explanations/streams/mapping.md), then attempt
   [MapBasics](../src/main/java/com/amigoscode/_7_streams/_1_basics/MapBasics.java).
5. Read [terminal operations and collection](explanations/streams/terminal-operations-and-collection.md), then attempt
   [ForEachAndCollect](../src/main/java/com/amigoscode/_7_streams/_1_basics/ForEachAndCollect.java).
6. Read [reduction basics](explanations/streams/reduction.md), then begin
   [ReduceBasics](../src/main/java/com/amigoscode/_7_streams/_1_basics/ReduceBasics.java)
   with TODOs 1–2. Read [Optional](explanations/foundations/optional.md)
   before the later TODOs that use the no-identity form.
7. Read [IntStream](explanations/streams/intstream.md), including primitive
   optional results, then attempt all six TODOs in
   [IntStreamBasics](../src/main/java/com/amigoscode/_7_streams/_2_intstreams/IntStreamBasics.java).
   Introduce each new operation before using it.
8. Read [generating sequences with iterate](explanations/streams/iteration.md),
   then attempt TODOs 1–2 in
   [IntStreamIterate](../src/main/java/com/amigoscode/_7_streams/_2_intstreams/IntStreamIterate.java).
   Introduce state-pair iteration and the LongStream/DoubleStream operations
   before assigning its later TODOs.
9. Revisit [LazyStreams](../src/main/java/com/amigoscode/_7_streams/_11_understanding/LazyStreams.java)
   to explain laziness, short-circuiting, single use, and ordering.

Some functional-interface TODOs contain small stream pipelines. Explain
the needed operation at that point; a code hint alone is not the refresher.

## Phase 3: broaden and combine

Continue through the stream packages: primitive operations, matching and
finding, transformations, `flatMap`, sorting, statistics, grouping, joining,
and collectors. Learn reduction carefully and revisit harder cases after
simpler pipelines are comfortable. Leave parallel-stream mechanics until
sequential behavior is clear.

Finish with [StreamsChallenge](../src/main/java/com/amigoscode/_7_streams/_12_challenge/StreamsChallenge.java).
Change its data and ask new questions after the initial attempt.

## Practice routine

- Predict the output and the type after each stage.
- Attempt independently; looking up API signatures is allowed.
- Ask for a small hint when stuck, then try again before requesting a solution.
- Check actual results, including relevant empty, duplicate, null, or no-match cases.
- Compare early patterns with loops. Use the form that communicates the task clearly.
- Explain the result in your own words during review.
- Reattempt selected problems later without copying the earlier answer.

There is no fixed prerequisite time limit. Use the
[self-check](revision/self-check.md) to find gaps. Exercise implementations
remain your work; the notes provide explanations and independent examples.

## Why the plan changed

The initial plan moved toward streams after a few correct submissions.
You then reported that Supplier and Consumer still felt unclear, and a
generation exercise introduced APIs before a proper explanation. The agreed
plan now covers the core-interface folder deliberately, while revising
already correct work through explanation rather than automatic full rewrites.

