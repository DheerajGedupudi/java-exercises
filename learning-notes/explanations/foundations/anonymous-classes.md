# Anonymous classes: the longer implementation form

[Home](../../README.md) · Previous: [Lambdas](lambdas.md) · Next: [Method references](method-references.md)

## Start with a named class

```java
class AscendingComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
        return Integer.compare(a, b);
    }
}
```

This class implements the interface's comparison contract. With `Comparator`
imported, you could create its object like this:

```java
Comparator<Integer> ascending = new AscendingComparator();
```

The left side declares the variable's reference type. The right side creates
an object of an implementing class. Interface-typed references can point to
instances of classes implementing that interface.

## Combine the definition and object creation

```java
Comparator<Integer> ascending = new Comparator<Integer>() {
    @Override
    public int compare(Integer a, Integer b) {
        return Integer.compare(a, b);
    }
};
```

Read this as: create an instance of an unnamed class implementing
`Comparator<Integer>`, using the class body inside those braces.

You are instantiating the unnamed implementation class. The syntax is not
a direct constructor call on the interface itself. The braces after the
parentheses introduce the anonymous class body, including its method declaration.
The final semicolon ends the enclosing variable declaration.

`@Override` asks the compiler to check that the method correctly overrides
or implements an inherited method. It does not call the method.

## A lambda for the same comparison behavior

```java
Comparator<Integer> ascending = (a, b) -> Integer.compare(a, b);
```

The functional interface supplies the method name and types. The lambda supplies
the parameters and implementation. Its body is the comparison expression,
whereas the anonymous class has a class body containing a method declaration.

An anonymous class can implement an interface or extend a class. A lambda
requires a functional-interface target. They also differ in language rules:
for example, a lambda's `this` refers to the surrounding instance, while an
anonymous class has its own `this`. Treat them as ways to express this example's
behavior, rather than assuming a lambda literally creates an anonymous class.

## A complete comparison

```java
import java.util.Comparator;

public class AnonymousClassesDemo {
    static class AscendingComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return Integer.compare(a, b);
        }
    }

    public static void main(String[] args) {
        Comparator<Integer> named = new AscendingComparator();
        Comparator<Integer> anonymous = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(a, b);
            }
        };
        Comparator<Integer> lambda = (a, b) -> Integer.compare(a, b);

        System.out.println(named.compare(4, 9) < 0);
        System.out.println(anonymous.compare(4, 9) < 0);
        System.out.println(lambda.compare(4, 9) < 0);
    }
}
```

Output:

```text
true
true
true
```

All three objects supply ascending comparison behavior. Assigning them does
not sort anything. Calling `compare` runs their behavior; a sort operation
calls the comparator as needed.

## Revise the original confusion

Explain the difference between these two shapes:

- `new Greeting() { ... }`: an anonymous class body with a method declaration.
- `(String name) -> { ... }`: lambda parameters and the method implementation body.

The ellipses are placeholders. Your [exercise](../../../src/main/java/com/amigoscode/_6_functionalinterfaces/_1_gettingstarted/FunctionalInterfaceBasics.java)
supplies the actual contract. Its [review](../../exercise-reviews/functional-interfaces/functional-interface-basics.md)
records the earlier syntax mistake and feedback.

