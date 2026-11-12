# MapBasics review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [MapBasics.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/MapBasics.java).

## Review: 2026-10-26

The source compiled and ran. All six TODOs were correct; all 31 output
lines matched expectations.

| TODO | Verified result |
|---|---|
| 1 | Uppercase names: `ALICE`, `BOB`, `CHARLIE`, `DIANA`, `EVE` |
| 2 | Word lengths: `4`, `7`, `3`, `8`, `5` |
| 3 | Squares: `1`, `4`, `9`, `16`, `25`, `36`, `49`, `64`, `81`, `100` |
| 4 | Five greetings with the first letter of each name uppercase and the rest lowercase |
| 5 | Even-number cubes: `8`, `64`, `216`, `512`, `1000` |
| 6 | Parsed integer list: `[10, 20, 30, 40, 50]` |

The greeting expression meets the requirement for the supplied names.
The combined filter/map pipeline correctly filters the original integers
before cubing the retained values.

Using `Stream.toList()` in TODO 6 is valid: this prompt asks for a resulting
list without prescribing a particular collector. That differs from the
explicit `Collectors.toList()` instruction in FilterBasics TODO 6.

## Lesson to carry forward

Mapping can preserve the element type or change it. The length mapping
changes strings into integers; the parsed-number mapping also changes the
element type. The terminal operation either prints the transformed elements
or returns a collection that the caller then prints.

Read [mapping](../../explanations/streams/mapping.md). Next, read
[terminal operations and collection](../../explanations/streams/terminal-operations-and-collection.md)
before attempting ForEachAndCollect. No Java source was edited in this review.

