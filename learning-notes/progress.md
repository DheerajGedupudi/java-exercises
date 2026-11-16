# Progress and next step

[Home](README.md) · [Roadmap](learning-plan.md) · [Protocol](protocol.md)

Latest recorded checkpoint: 2026-11-12 (loose-ends recheck). This file
records verified reviews, not live checks of edits made afterward. Detailed
findings and earlier checkpoints are in the linked exercise reviews.

## Next step

1. **Loose ends quick round.** Rechecked on 2026-11-12: LambdaExpressions
   is fixed. Four are still open and assigned: FunctionalInterfaceBasics
   TODO 6, FunctionExercise TODO 5, BiFunctionExercise TODOs 4–5, and
   ForEachAndCollect TODO 6. See [loose ends](learning-plan.md#loose-ends).

   Brief given on 2026-11-12:
   - FunctionalInterfaceBasics 6, Function 5, and ForEachAndCollect 6:
     reread the TODO line. Optional reading:
     [number-to-word alternatives](explanations/functional-interfaces/function.md#alternatives-for-a-fixed-number-to-word-mapping),
     answering the learner's comment in the source, and the
     [terminal operations example](explanations/streams/terminal-operations-and-collection.md#a-complete-example).
   - BiFunction TODO 4: read
     [andThen](explanations/functional-interfaces/bifunction.md#andthen-builds-behavior-before-apply-runs-it).
   - BiFunction TODO 5: read the
     [index-range pairing](explanations/streams/intstream.md#index-ranges-combine-two-lists-position-by-position)
     section, which was added for this TODO.
   - Write `// predict:` comments, go through the
     [checklist](revision/mistake-patterns.md#checklist-before-asking-for-a-review),
     then ask for a review.

   The warm-up question asked on 2026-11-12 was the IntStreamBasics one in
   open questions. It hasn't been answered yet.
2. **Then [LazyStreams](../src/main/java/com/amigoscode/_7_streams/_11_understanding/LazyStreams.java)**
   (Stage 3, #1), full routine. The brief introduces `peek` and
   short-circuiting before the attempt.

## Open questions

These explain-it-back questions haven't been answered yet. One or two come
back as a warm-up at the start of each session (see
[protocol step 0](protocol.md#0-start-of-each-session-assistant)). A sentence
or two is enough. An answered question moves into its exercise's review.

| Asked | Exercise | Question |
|---|---|---|
| 2026-11-11 | [MinMax](exercise-reviews/streams/min-max.md) | With an empty people list, what did the old `orElse(null)` version do, and what does the current version do? |
| 2026-11-11 | [MinMax](exercise-reviews/streams/min-max.md) | When would `orElseThrow` be a better choice than skipping? |
| 2026-11-11 | [MinMax](exercise-reviews/streams/min-max.md) | `comparingInt(Person::age)` compares ages. Why does `min` return an `Optional<Person>` and not an `int`? |
| 2026-10-31 | [IntStreamIterate](exercise-reviews/streams/intstream-iterate.md) | In the Fibonacci pipeline, why can `limit` go before or after `mapToInt`, when moving it across a `filter` can change the result? |
| 2026-10-31 | [IntStreamIterate](exercise-reviews/streams/intstream-iterate.md) | Which endpoints do `range(1, n)` and `rangeClosed(1, n)` include? |
| 2026-10-27 | [IntStreamBasics](exercise-reviews/streams/intstream-basics.md) | Why do two `IntStream.of(...)` calls work, when two terminal calls on the same stream variable fail? |
| 2026-10-27 | [ReduceBasics](exercise-reviews/streams/reduce-basics.md) | What do `reduce(0, Integer::sum)` and `reduce(Integer::sum)` return for an empty stream, and for a stream containing only `7`? |

## Exercise status

| Exercise | Last verified code status | Understanding checkpoint |
|---|---|---|
| [FunctionalInterfaceBasics](exercise-reviews/functional-interfaces/functional-interface-basics.md) | TODOs 1–5 correct; TODO 6 greeting-text mismatch at its last review | Explain contract → implementation → invocation |
| [LambdaExpressions](exercise-reviews/functional-interfaces/lambda-expressions.md) | All seven correct; capitalization fix verified on 2026-11-12 (all eight output lines) | Explain returning versus printing and zero-input lambdas |
| [MethodReferences](exercise-reviews/functional-interfaces/method-references.md) | All six correct; output verified after fixing the name conflict | Translate each reference into a lambda |
| [Supplier](exercise-reviews/functional-interfaces/supplier.md) | All six correct after adding the first invocation and print | Explain timing, repeated calls, and fresh versus shared results |
| [Consumer](exercise-reviews/functional-interfaces/consumer.md) | All seven correct after adding the map print; output and entries verified | Explain actions, `accept`, and same-input chaining |
| [Function](exercise-reviews/functional-interfaces/function.md) | Behavior and printed output correct; TODO 5 needs the requested name `intToWord` | Explain transformation and composition |
| [BiFunction](exercise-reviews/functional-interfaces/bifunction.md) | TODOs 1–3 correct on inspection; 4 partial and 5 unimplemented at review | Explain two inputs, one result, and `andThen` |
| [Predicate](exercise-reviews/functional-interfaces/predicate.md) | All seven correct; all 14 output lines verified | Explain short-circuiting and the filtering predicate |
| [CreatingStreams](exercise-reviews/streams/creating-streams.md) | All six correct after the inclusive range correction; output and generated count verified | Explain supplier-based generation, limiting, and consumption |
| [FilterBasics](exercise-reviews/streams/filter-basics.md) | All six correct after condition and collector corrections; all 20 output lines verified | Explain predicate wording, chained filters, and terminal result types |
| [MapBasics](exercise-reviews/streams/map-basics.md) | All six correct; all 31 output lines verified | Explain transformation types and the input seen after each stage |
| [ForEachAndCollect](exercise-reviews/streams/foreach-and-collect.md) | TODOs 1–5 correct; 6 has correct factory and values but needs the class name printed | Explain terminal results, set contents, unmodifiable lists, and collection factories |
| [ReduceBasics](exercise-reviews/streams/reduce-basics.md) | All six correct after correcting TODOs 3 and 6; all six output lines verified | Explain the two overloads, empty/singleton results, Optional extraction, and original elements versus partial results |
| [IntStreamBasics](exercise-reviews/streams/intstream-basics.md) | All six correct after adding min/max from separate streams; all 19 output lines verified | Explain source values versus separate stream instances, boxing, and primitive optional results |
| [IntStreamIterate](exercise-reviews/streams/intstream-iterate.md) | All five correct after the inclusive endpoint correction; 35 deterministic lines and five formatted random lines verified on 2026-10-31 | Explain Fibonacci state pairs, limit placement, inclusive/exclusive endpoints, and supplier-based generation |
| [MinMax](exercise-reviews/streams/min-max.md) | All six correct; all six output lines re-verified on 2026-11-11; TODO 4 now skips safely when people is empty (checked with a scratch variant) | Explain comparison keys versus selected elements, ties, no-identity reduction, and Optional fallback policies |

The understanding column lists broader revision topics for each exercise.
A successful run doesn't show mastery; only an explanation does. Specific
pending questions are under [open questions](#open-questions).

## How to update this record

Follow the [protocol](protocol.md). After a review:

- update the code status with what was actually checked, using the
  protocol's evidence terms;
- add new explain-it-back questions to open questions, and remove answered
  ones;
- keep exactly one next step here, so old reviews don't turn into
  conflicting assignments.
