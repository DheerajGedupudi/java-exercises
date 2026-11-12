# Progress and next step

[Home](README.md) · [Study order](learning-plan.md)

Latest recorded checkpoint: 2026-11-11. This file records verified reviews,
not an automatic live assessment of edits made afterward. Detailed findings
and earlier checkpoints belong in the linked exercise reviews.

## Immediate next step

MinMax passes all six TODOs. On 2026-11-11 the revised source ran with
all six lines unchanged: 3, 91, Hi, Diana 22, 91, and -1. TODO 4 now keeps
the Optional and checks it before use. A scratch variant with an empty
people list was also run: TODO 4 printed nothing and did not throw, so the
earlier NullPointerException finding is resolved.

Next understanding checkpoint: explain in words what the original
`orElse(null)` version did with an empty people list, when `orElseThrow`
would fit better than skipping, and why `min` returns an Optional of
Person rather than an int age. The code shows the skip-when-absent policy;
the learner's explanation has not yet been checked.

The earlier LazyStreams lesson was proposed but has not started. The
learner proceeded with MinMax; review this checkpoint before setting a new
exercise assignment.

IntStreamIterate's earlier checkpoint passes all five TODOs, including
the inclusive sum of 500000500000. That source was not rerun in the MinMax
review.

For revision, revisit Fibonacci state pairs and limit placement. Both
placements were checked and produce the same ten values; explaining why
the one-to-one projection permits the move while filtering can change
which elements are counted remains an understanding checkpoint.

The earlier confusion about reusing streams is corrected in the code;
explaining separate factory calls versus a consumed stream instance remains
a useful understanding checkpoint.

The two reduction overloads remain a revision checkpoint: predict their
return types and values for empty and singleton inputs. The learner
reported that omitting the identity was unfamiliar; a correct submission
does not by itself verify that explanation.

The earlier ForEachAndCollect class-name print, Function naming correction,
and remaining BiFunction TODOs 4–5 remain recorded below at their last
reviewed states; they were not rechecked during this review.

- [MinMax review](exercise-reviews/streams/min-max.md)
- [Minimum and maximum](explanations/streams/min-max.md)
- [MinMax.java](../src/main/java/com/amigoscode/_7_streams/_3_minmax/MinMax.java)
- [ReduceBasics review](exercise-reviews/streams/reduce-basics.md)
- [Reduction refresher](explanations/streams/reduction.md)
- [Optional](explanations/foundations/optional.md)
- [IntStream](explanations/streams/intstream.md)
- [IntStreamBasics review](exercise-reviews/streams/intstream-basics.md)
- [IntStreamBasics.java](../src/main/java/com/amigoscode/_7_streams/_2_intstreams/IntStreamBasics.java)
- [Generating sequences with iterate](explanations/streams/iteration.md)
- [IntStreamIterate review](exercise-reviews/streams/intstream-iterate.md)
- [IntStreamIterate.java](../src/main/java/com/amigoscode/_7_streams/_2_intstreams/IntStreamIterate.java)
- [LazyStreams.java](../src/main/java/com/amigoscode/_7_streams/_11_understanding/LazyStreams.java)
- [ReduceBasics.java](../src/main/java/com/amigoscode/_7_streams/_1_basics/ReduceBasics.java)

## Exercise status

| Exercise | Last verified code status | Understanding checkpoint |
|---|---|---|
| [FunctionalInterfaceBasics](exercise-reviews/functional-interfaces/functional-interface-basics.md) | TODOs 1–5 correct; TODO 6 greeting-text mismatch at its last review | Explain contract → implementation → invocation |
| [LambdaExpressions](exercise-reviews/functional-interfaces/lambda-expressions.md) | Lambda behavior correct; last recorded issue was capitalization in the Callable result | Explain returning versus printing and zero-input lambdas |
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

The understanding column gives questions to answer. It does not claim
mastery from a successful run. The user reported incomplete clarity on the
core interfaces, so the current study phase addresses that directly.

## How to update this record

After a review, update the code status with what was actually checked.
Record limitations such as an uninvoked function, failed compilation, or
unverified later edits. Update understanding from the learner's explanation
and self-report. Keep one next step here so old reviews do not become
conflicting current assignments.

