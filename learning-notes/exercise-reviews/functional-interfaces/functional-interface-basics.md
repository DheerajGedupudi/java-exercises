# FunctionalInterfaceBasics review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [FunctionalInterfaceBasics.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_1_gettingstarted/FunctionalInterfaceBasics.java).

## Earlier syntax checkpoints

The attempt initially wrapped a lambda in `new Greeting(...)`. Assigning the
lambda directly to the interface-typed variable was the required syntax.
The statement inside its block also needed its own semicolon.

Full and shorter lambda forms were then explained. Both supply the same
greeting behavior through the `greet(String)` contract.

## First complete review

The source compiled and ran. TODOs 1–5 were correct: the interface, anonymous
class, full lambda, shorter lambda, and calls on each implementation.

The output at that checkpoint was:

```text
Hello, Alice!
Hello, Alice!
Hello, Alice!
Hello, Bob!
```

TODO 6 correctly passed a lambda to the helper, but the requested final
message was `Hey there, Bob!`. The greeting text in the lambda needed to match.
No later verification of that text correction is recorded here.

## Lesson to carry forward

Passing behavior to a helper and invoking it are separate steps. The helper
calls the interface's method; the supplied lambda determines the message.
Valid Java can still miss a required output string.

Read [functional interfaces](../../explanations/foundations/functional-interfaces.md),
[lambda syntax](../../explanations/foundations/lambdas.md), and
[anonymous classes](../../explanations/foundations/anonymous-classes.md).

