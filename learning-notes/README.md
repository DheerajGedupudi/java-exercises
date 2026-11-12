# Java functional interfaces and streams

A study guide built from the concepts and mistakes encountered in this repo.
Use it to understand an API before attempting an exercise, then return for revision.

## Start here

1. Check [progress and the next step](progress.md).
2. Follow the [learning plan](learning-plan.md).
3. Read the relevant concept chapter, run its example, and predict the result.
4. Attempt the linked Java exercise yourself and request a review.
5. Use the [quick reference](revision/quick-reference.md) and [self-check](revision/self-check.md) for recall.

Each complete example program includes imports and expected output. Other
code fences show signatures or fragments with their required context. Alternative
declarations are shown separately; do not paste them into one scope as duplicates.

## Concept chapters

### Foundations

| Chapter | Main question |
|---|---|
| [Functional interfaces](explanations/foundations/functional-interfaces.md) | How can a method receive behavior? |
| [Lambdas](explanations/foundations/lambdas.md) | What do the parameters and body implement, and when do they run? |
| [Anonymous classes](explanations/foundations/anonymous-classes.md) | What does `new Interface() { ... }` actually create? |
| [Method references](explanations/foundations/method-references.md) | What does `::` mean in each target context? |
| [Optional](explanations/foundations/optional.md) | How do I handle a result that might be absent? |

### Core functional interfaces

| Chapter | Contract |
|---|---|
| [Supplier](explanations/functional-interfaces/supplier.md) | No input, one supplied result |
| [Consumer](explanations/functional-interfaces/consumer.md) | An input and an action, with no returned result |
| [Function](explanations/functional-interfaces/function.md) | One input transformed into a result |
| [BiFunction](explanations/functional-interfaces/bifunction.md) | Two inputs and one result |
| [Predicate](explanations/functional-interfaces/predicate.md) | One input tested for a boolean answer |

### Streams

| Chapter | Main question |
|---|---|
| [Pipeline overview](explanations/streams/overview.md) | What are sources, intermediate operations, and terminal operations? |
| [Creating streams](explanations/streams/creating-streams.md) | How do existing and generated sources work? |
| [Filtering](explanations/streams/filtering.md) | How does a predicate select elements, and what triggers execution? |
| [Mapping](explanations/streams/mapping.md) | How does a function transform each element and possibly change its type? |
| [Terminal operations and collection](explanations/streams/terminal-operations-and-collection.md) | How do actions, lists, sets, and concrete collection factories differ? |
| [Reduction](explanations/streams/reduction.md) | How do identity values and combining operations produce one result? |
| [IntStream](explanations/streams/intstream.md) | Why have a primitive stream, and why does `chars()` return one? |
| [Generating sequences with iterate](explanations/streams/iteration.md) | How do a seed, a next-value rule, and a stopping rule generate a sequence? |
| [Minimum and maximum](explanations/streams/min-max.md) | How do comparators select an element, and how should empty results be handled? |

## Exercise reviews

These are dated checkpoints, not live grades of later source edits. The
[progress tracker](progress.md) consolidates the latest recorded result.

| Exercise | Review |
|---|---|
| FunctionalInterfaceBasics | [Syntax and initial complete attempt](exercise-reviews/functional-interfaces/functional-interface-basics.md) |
| LambdaExpressions | [Return values, naming, and the shorter lambda](exercise-reviews/functional-interfaces/lambda-expressions.md) |
| MethodReferences | [Scope error and successful follow-up](exercise-reviews/functional-interfaces/method-references.md) |
| Supplier | [Initial missing invocation and completed follow-up](exercise-reviews/functional-interfaces/supplier.md) |
| Consumer | [Action chaining, traversal, and map output](exercise-reviews/functional-interfaces/consumer.md) |
| Function | [Composition, number-word mapping, and alternatives](exercise-reviews/functional-interfaces/function.md) |
| BiFunction | [Partial attempt and composition](exercise-reviews/functional-interfaces/bifunction.md) |
| Predicate | [Combined conditions and completed filtering](exercise-reviews/functional-interfaces/predicate.md) |
| CreatingStreams | [Sources, generation, and completed range correction](exercise-reviews/streams/creating-streams.md) |
| FilterBasics | [Containment versus prefixes and list collection](exercise-reviews/streams/filter-basics.md) |
| MapBasics | [Verified transformations, greetings, and parsing](exercise-reviews/streams/map-basics.md) |
| ForEachAndCollect | [Collection contents and the remaining class-name output](exercise-reviews/streams/foreach-and-collect.md) |
| ReduceBasics | [Initial corrections and completed follow-up review](exercise-reviews/streams/reduce-basics.md) |
| IntStreamBasics | [Initial review and completed min/max follow-up](exercise-reviews/streams/intstream-basics.md) |
| IntStreamIterate | [Verified sequences, limit placement, and endpoint wording](exercise-reviews/streams/intstream-iterate.md) |
| MinMax | [Verified selections, reduction, and empty-result handling](exercise-reviews/streams/min-max.md) |

## Setup

- [Git: clone, fork, branch, and remotes](setup/git.md)
- [Java tooling and running a self-contained exercise](setup/java-tooling.md)

## How this folder stays organized

- `explanations/`: one main chapter per concept, with purpose, contracts,
  syntax, examples, execution walkthroughs, common confusions, and practice links.
- `exercise-reviews/`: one file per exercise; append checkpoints and link to
  the explanation instead of repeating whole lessons.
- `revision/`: condensed lookup tables and questions with revealable answers.
- `setup/`: repository and environment information.
- `learning-plan.md`: curriculum. `progress.md`: latest checkpoint and next step.

Use descriptive filenames and keep reading order in the learning plan.
Distinguish verified output from confidence explaining the concept. Add new
learning explanations during the same session, following the repository's
[agent instructions](../AGENTS.md).
