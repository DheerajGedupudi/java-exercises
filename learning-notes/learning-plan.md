# Learning plan and roadmap

[Home](README.md) · [Protocol](protocol.md) · [Current progress](progress.md)

## Goal

**Starting point:** strong DSA, with 1000+ LeetCode problems solved in Java.
Very little production code experience, and no streams knowledge at the start.

**Why:** an interview for a Java engineer role pointed out the missing
streams knowledge. Streams have also long felt unfamiliar, and that feeling
should go.

**Success means:**

1. Using and explaining streams confidently in a Java interview.
2. Stream code feeling as familiar as a `for` loop.

Most of the work is the declarative style that LeetCode doesn't build: for
example, `Optional` instead of sentinel values, collectors instead of
hand-built maps, and comparators instead of manual comparisons. Problem
solving itself is already strong.

## How to use this roadmap

- Work top to bottom. Each exercise follows the [protocol](protocol.md).
- **Full** exercises get the full routine. **Quick** exercises get a quick
  round, because the notes already cover most of their concepts.
- Tick an exercise when it counts as done under the protocol.
- Stages 1–2 were completed before the protocol existed. Their remaining
  understanding questions are tracked in [progress](progress.md).

## Loose ends

At their last review, these still had small open items. One quick round
rechecks the current code first, since some may already be fixed.

- [ ] FunctionalInterfaceBasics TODO 6: greeting text didn't match.
- [ ] LambdaExpressions: capitalization in the Callable result.
- [ ] FunctionExercise TODO 5: use the requested name `intToWord`.
- [ ] BiFunctionExercise: TODO 4 partial, TODO 5 not started.
- [ ] ForEachAndCollect TODO 6: print the class name.

## Stage 1: functional interfaces

| Done | Exercise | Chapter | Review |
|---|---|---|---|
| ☐ | [FunctionalInterfaceBasics] | [Functional interfaces](explanations/foundations/functional-interfaces.md) | [Review](exercise-reviews/functional-interfaces/functional-interface-basics.md) |
| ☐ | [LambdaExpressions] | [Lambdas](explanations/foundations/lambdas.md) | [Review](exercise-reviews/functional-interfaces/lambda-expressions.md) |
| ☑ | [MethodReferences] | [Method references](explanations/foundations/method-references.md) | [Review](exercise-reviews/functional-interfaces/method-references.md) |
| ☑ | [SupplierExercise] | [Supplier](explanations/functional-interfaces/supplier.md) | [Review](exercise-reviews/functional-interfaces/supplier.md) |
| ☑ | [ConsumerExercise] | [Consumer](explanations/functional-interfaces/consumer.md) | [Review](exercise-reviews/functional-interfaces/consumer.md) |
| ☐ | [FunctionExercise] | [Function](explanations/functional-interfaces/function.md) | [Review](exercise-reviews/functional-interfaces/function.md) |
| ☐ | [BiFunctionExercise] | [BiFunction](explanations/functional-interfaces/bifunction.md) | [Review](exercise-reviews/functional-interfaces/bifunction.md) |
| ☑ | [PredicateExercise] | [Predicate](explanations/functional-interfaces/predicate.md) | [Review](exercise-reviews/functional-interfaces/predicate.md) |

The unticked rows are waiting on the loose ends above.

## Stage 2: stream basics

| Done | Exercise | Chapter | Review |
|---|---|---|---|
| ☑ | [CreatingStreams] | [Overview](explanations/streams/overview.md), [Creating streams](explanations/streams/creating-streams.md) | [Review](exercise-reviews/streams/creating-streams.md) |
| ☑ | [FilterBasics] | [Filtering](explanations/streams/filtering.md) | [Review](exercise-reviews/streams/filter-basics.md) |
| ☑ | [MapBasics] | [Mapping](explanations/streams/mapping.md) | [Review](exercise-reviews/streams/map-basics.md) |
| ☐ | [ForEachAndCollect] | [Terminal operations](explanations/streams/terminal-operations-and-collection.md) | [Review](exercise-reviews/streams/foreach-and-collect.md) |
| ☑ | [ReduceBasics] | [Reduction](explanations/streams/reduction.md), [Optional](explanations/foundations/optional.md) | [Review](exercise-reviews/streams/reduce-basics.md) |
| ☑ | [IntStreamBasics] | [IntStream](explanations/streams/intstream.md) | [Review](exercise-reviews/streams/intstream-basics.md) |
| ☑ | [IntStreamIterate] | [Iterate](explanations/streams/iteration.md) | [Review](exercise-reviews/streams/intstream-iterate.md) |
| ☑ | [MinMax] | [Min and max](explanations/streams/min-max.md) | [Review](exercise-reviews/streams/min-max.md) |

## Stage 3: the rest of streams

107 TODOs. Quick rounds sit next to the full exercise they relate to, so
related ideas stay together and earlier topics come back for review.

| Done | # | Exercise | Tier | TODOs | What it practices |
|---|---|---|---|---|---|
| ☐ | 1 | [LazyStreams] | Full | 5 | Laziness, short-circuiting, single use, intermediate vs terminal |
| ☐ | 2 | [FindAndMatch] | Full | 7 | `anyMatch`/`allMatch`/`noneMatch`, `findFirst`/`findAny`, Optional handling |
| ☐ | 3 | [TakeWhileDropWhile] | Quick | 6 | Stopping at the first non-match vs filtering everything |
| ☐ | 4 | [FlatMapExercise] | Full | 7 | `map` vs `flatMap`, flattening nested data |
| ☐ | 5 | [MapExercise] | Quick | 6 | Mapping records to DTOs, primitive mapping |
| ☐ | 6 | [DistinctExercise] | Full | 6 | Duplicates, `distinct` vs a Set, record equality, TreeSet |
| ☐ | 7 | [SortingExercise] | Full | 7 | Comparators, reversed order, sorting by several fields |
| ☐ | 8 | [FilterExercise] | Quick | 7 | Review of filtering, reusable predicates |
| ☐ | 9 | [JoiningExercise] | Full | 5 | Building strings with `joining` |
| ☐ | 10 | [AverageAndSum] | Quick | 6 | Summing and averaging collectors, needed inside grouping |
| ☐ | 11 | [GroupingBy] | Full | 7 | Grouping, downstream collectors, nested grouping |
| ☐ | 12 | [CountMinMax] | Quick | 6 | `counting`, `minBy`/`maxBy` collectors |
| ☐ | 13 | [GroupingAndCounting] | Full | 6 | Frequency counts, most common item, partitioning |
| ☐ | 14 | [SummaryStatistics] | Quick | 5 | Count, sum, min, max, and average in one pass |
| ☐ | 15 | [CollectorsExercise] | Full | 7 | `toMap` with duplicate keys, `collectingAndThen`, `teeing` |
| ☐ | 16 | [ReduceExercise] | Quick | 6 | `reduce` vs `collect`, the three-argument form |
| ☐ | 17 | [StreamsChallenge] | Full | 8 | Interview-style tasks combining everything |
| ☐ | 18 | Interview drills | Full | — | Classic interview prompts; the notes file is written after #15 |

## After streams

These are listed but not scheduled. Pick them up once Stage 3 is done.

| Exercise or course | TODOs | Value | Why |
|---|---|---|---|
| [OptionalExercises] | 7 | High | Every stream search returns an Optional; could move before #2 |
| [MapAdvanced] | 6 | High | `merge` and `computeIfAbsent` are what grouping does internally; could move before #11 |
| `_5_generics` | 70 | High | Reading stream signatures such as `Function<? super T, ? extends R>`; common interview topic |
| `_3_oop` | 91 | High | Encapsulation, abstract classes vs interfaces, polymorphism, SOLID: interview staples |
| `_8_testing` (tests in `src/test`) | 127 | High for jobs | JUnit, AssertJ, and Mockito: the biggest production gap |
| `_2_developers`: exceptions, dates, BigDecimal, enums | 32 | Selected | Everyday production code |
| `_6_functionalinterfaces`: `_3_extending`, `_4_callbacks` | 25 | Optional | How `Predicate.and()`-style composition is built |

Not planned: `_1_beginners` and the rest of `_4_datastructures` and
`_2_developers`, which 1000+ LeetCode problems already cover.

## Plan history

- **October 2026:** began with functional interfaces, then moved toward
  streams. Supplier and Consumer still felt unclear, so the core
  interfaces were covered deliberately before resuming the stream basics.
- **12 November 2026:** reorganized as this roadmap. The interview goal was
  recorded, every exercise was given a tier, all streams TODOs were
  scheduled, and the [protocol](protocol.md) was introduced.

[FunctionalInterfaceBasics]: ../src/main/java/com/amigoscode/_6_functionalinterfaces/_1_gettingstarted/FunctionalInterfaceBasics.java
[LambdaExpressions]: ../src/main/java/com/amigoscode/_6_functionalinterfaces/_1_gettingstarted/LambdaExpressions.java
[MethodReferences]: ../src/main/java/com/amigoscode/_6_functionalinterfaces/_1_gettingstarted/MethodReferences.java
[SupplierExercise]: ../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/SupplierExercise.java
[ConsumerExercise]: ../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/ConsumerExercise.java
[FunctionExercise]: ../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/FunctionExercise.java
[BiFunctionExercise]: ../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/BiFunctionExercise.java
[PredicateExercise]: ../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/PredicateExercise.java
[CreatingStreams]: ../src/main/java/com/amigoscode/_7_streams/_1_basics/CreatingStreams.java
[FilterBasics]: ../src/main/java/com/amigoscode/_7_streams/_1_basics/FilterBasics.java
[MapBasics]: ../src/main/java/com/amigoscode/_7_streams/_1_basics/MapBasics.java
[ForEachAndCollect]: ../src/main/java/com/amigoscode/_7_streams/_1_basics/ForEachAndCollect.java
[ReduceBasics]: ../src/main/java/com/amigoscode/_7_streams/_1_basics/ReduceBasics.java
[IntStreamBasics]: ../src/main/java/com/amigoscode/_7_streams/_2_intstreams/IntStreamBasics.java
[IntStreamIterate]: ../src/main/java/com/amigoscode/_7_streams/_2_intstreams/IntStreamIterate.java
[MinMax]: ../src/main/java/com/amigoscode/_7_streams/_3_minmax/MinMax.java
[LazyStreams]: ../src/main/java/com/amigoscode/_7_streams/_11_understanding/LazyStreams.java
[FindAndMatch]: ../src/main/java/com/amigoscode/_7_streams/_5_filtering/FindAndMatch.java
[TakeWhileDropWhile]: ../src/main/java/com/amigoscode/_7_streams/_5_filtering/TakeWhileDropWhile.java
[FlatMapExercise]: ../src/main/java/com/amigoscode/_7_streams/_6_transformations/FlatMapExercise.java
[MapExercise]: ../src/main/java/com/amigoscode/_7_streams/_6_transformations/MapExercise.java
[DistinctExercise]: ../src/main/java/com/amigoscode/_7_streams/_4_duplicates/DistinctExercise.java
[SortingExercise]: ../src/main/java/com/amigoscode/_7_streams/_8_sorting/SortingExercise.java
[FilterExercise]: ../src/main/java/com/amigoscode/_7_streams/_5_filtering/FilterExercise.java
[JoiningExercise]: ../src/main/java/com/amigoscode/_7_streams/_9_joiningstrings/JoiningExercise.java
[AverageAndSum]: ../src/main/java/com/amigoscode/_7_streams/_7_statistics/AverageAndSum.java
[GroupingBy]: ../src/main/java/com/amigoscode/_7_streams/_7_statistics/GroupingBy.java
[CountMinMax]: ../src/main/java/com/amigoscode/_7_streams/_7_statistics/CountMinMax.java
[GroupingAndCounting]: ../src/main/java/com/amigoscode/_7_streams/_7_statistics/GroupingAndCounting.java
[SummaryStatistics]: ../src/main/java/com/amigoscode/_7_streams/_7_statistics/SummaryStatistics.java
[CollectorsExercise]: ../src/main/java/com/amigoscode/_7_streams/_10_collectors/CollectorsExercise.java
[ReduceExercise]: ../src/main/java/com/amigoscode/_7_streams/_6_transformations/ReduceExercise.java
[StreamsChallenge]: ../src/main/java/com/amigoscode/_7_streams/_12_challenge/StreamsChallenge.java
[OptionalExercises]: ../src/main/java/com/amigoscode/_2_developers/_14_nullhandling/OptionalExercises.java
[MapAdvanced]: ../src/main/java/com/amigoscode/_4_datastructures/_7_maps/MapAdvanced.java
