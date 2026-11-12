# SupplierExercise review

[Home](../../README.md) · [Latest progress](../../progress.md)

Exercise: [SupplierExercise.java](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/SupplierExercise.java).

## Initial review: 2026-10-25

The file compiled and ran. TODOs 2–6 were correct. TODO 1 defined the
`helloSupplier` lambda but did not invoke it or print its returned value.
The missing statement was `System.out.println(helloSupplier.get());`.

## Follow-up: complete

The user added the invocation and print. The revised source compiled and
ran with all six TODOs meeting their requirements.

| Check | Evidence from the run |
|---|---|
| Constant supplier | Printed `Hello World` |
| Random supplier | Printed three numeric values, each in `[0.0, 1.0)` |
| List factory | Printed `[A]` for the first list and `[]` for the second |
| Deferred body | Printed `Before get()`, `Computing...`, `Result`, and `After get()` in that order |
| Time supplier | Printed an ISO-style local date-time |
| Repeated generation helper | Printed a list containing five numeric values, each in `[0.0, 1.0)` |

The first run had 11 lines because `Hello World` was missing; the corrected
run had 12. Exact random values and the timestamp vary. Distinct random
values are not guaranteed or required by the contract.

## Lesson to carry forward

The supplier variable holds behavior. `get()` invokes it and returns a value;
`println` makes that value visible. A constructor-reference factory creates
independent objects because its implementation invokes a constructor on each
request. Deferred execution does not automatically add caching.

Read [Supplier](../../explanations/functional-interfaces/supplier.md).
The next core-interface exercise is Consumer. Passing review establishes
the observed behavior; explain the contract and execution timing as a
separate understanding check.

