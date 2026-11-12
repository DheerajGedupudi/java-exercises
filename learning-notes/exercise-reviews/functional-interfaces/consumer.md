# ConsumerExercise review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [ConsumerExercise.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/ConsumerExercise.java).

## Review: 2026-10-25

The source compiled and ran. TODOs 1–6 were correct; all 13 printed lines
matched their expected greetings, uppercase strings, age description,
and list traversal outputs.

| TODO | Review result |
|---|---|
| 1 | Correct greeter, invoked with `accept` |
| 2 | Correct uppercase-printing consumer |
| 3 | Correct `greeter.andThen(shouter)`, with greeting before uppercase output |
| 4 | Correct two-input `BiConsumer` for name and age |
| 5 | Correctly passed `greeter` to the list's `forEach` |
| 6 | Correctly passed `shouter` and the list to the helper |
| 7 | Correctly inserted all three score entries, but did not print the map |

The remaining requirement at this checkpoint is to add
`System.out.println(scores);` after the three `addToMap.accept` calls.
Expected entries are `Alice=95`, `Bob=87`, and `Charlie=92`.
Their printed order is unspecified because the exercise uses `HashMap`.
The map mutations were checked from the code; the current run did not print them.

## Follow-up: complete

The user added the map print. The revised source compiled and ran with 14
output lines. The first 13 matched the earlier checks, and the final line
contained exactly `Alice=95`, `Bob=87`, and `Charlie=92`. The entries were
checked independently of their printed order. All seven TODOs now meet
their requirements.

A subsequent check on 2026-10-25 confirmed the same 14-line output and all
three map entries, again without relying on `HashMap` iteration order.

## Lesson to carry forward

`Consumer.andThen` passes the original input to the first action and then
the second action. The greeting's printed text is not a returned value
fed into the shouter. The map consumer performs a mutation as its action;
printing the resulting map is another action required by this prompt.

Read [Consumer](../../explanations/functional-interfaces/consumer.md), then
continue with [Function](../../explanations/functional-interfaces/function.md)
to compare action chaining with result chaining.

