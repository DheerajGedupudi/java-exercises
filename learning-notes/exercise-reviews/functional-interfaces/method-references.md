# MethodReferences review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [MethodReferences.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_1_gettingstarted/MethodReferences.java).

## Initial review: 2026-10-23

Compilation failed because an enclosing local variable was named `s`, and
TODO 6 also named its lambda parameter `s`:

```text
error: variable s is already defined in method main(String[])
```

The method references matched the prompts on inspection, but output could
not be checked until the compilation error was fixed.

## Follow-up: complete

The local variable was renamed to `string` and the lambda parameter to
`text`. Either change alone would have resolved the conflict; both are valid.
The revised source compiled and ran, with all nine lines matching expectations:

```text
42
Hello from method ref!
HELLO
[]
Alice
Bob
Charlie
5
5
```

All six TODOs met their requirements. The lambda and method reference
both returned `5` for `"Hello"`.

## Lesson to carry forward

A lambda parameter cannot shadow an enclosing local variable. Its name is
your choice; the target interface determines its type and contract.
The static, bound-instance, input-instance, and constructor references all
worked in their expected target contexts.

Read [method references](../../explanations/foundations/method-references.md)
and [lambda scope](../../explanations/foundations/lambdas.md#parameter-scope).

