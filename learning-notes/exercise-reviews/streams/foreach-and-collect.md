# ForEachAndCollect review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [ForEachAndCollect.java](../../../src/main/java/com/amigoscode/_7_streams/_1_basics/ForEachAndCollect.java).

## Review: 2026-10-26

The source compiled and ran, printing 21 lines. All printed values and
collection contents matched expectations. One required output was absent.

| TODO | Review result |
|---|---|
| 1 | Correct lambda-based printing of all seven language values, including repetitions |
| 2 | Correct method-reference printing of one through ten |
| 3 | Correct collected even-number list: `[2, 4, 6, 8, 10]` |
| 4 | Correct five unique language values; checked without requiring set iteration order |
| 5 | Correct use of `Collectors.toUnmodifiableList()` and correct printed values |
| 6 | Correct `LinkedList::new` factory and list contents; missing the class-name print |

## Remaining requirement

After printing `result4`, add:

```java
System.out.println(result4.getClass().getName());
```

This should print `java.util.LinkedList`. `getSimpleName()` is another
way to display the type, producing `LinkedList`.

The declaration `List<String> result4` is valid. It describes the variable's
reference type; the supplied constructor determines the actual object class.
You do not have to change the variable type to invoke `getClass()`.

The original run verified the printed list's contents. The correct factory
was checked from the source; the missing print is the exercise's requested
visible confirmation of that runtime class.

## Next topic

Read [reduction basics](../../explanations/streams/reduction.md), then begin
ReduceBasics TODOs 1–2 after adding the class-name print. Start with the
identity value and the two-input combining operation before the Optional form.

The [terminal-operations chapter](../../explanations/streams/terminal-operations-and-collection.md)
explains the collection choices. No Java source was edited during this review.

