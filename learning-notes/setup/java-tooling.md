# Java tooling snapshot

[Home](../README.md) · [Git setup](git.md)

The repository's `pom.xml` targets Java 25. The initial setup check found
that the shell's `java` reported JDK 25 while Maven ran on JDK 27. This is
a historical observation, not a claim about every later terminal or IDE run.

Check the active versions with:

```bash
java -version
mvn -version
```

The IDE project SDK, the JDK used by its Maven runner, and the terminal's
`java` can be configured differently. Using JDK 25 consistently for this
project avoids mixing those environments while learning.

The recorded exercise reviews used Java's source-file launcher from the
repository root, for example:

```bash
java src/main/java/com/amigoscode/_6_functionalinterfaces/_2_functionalinterfaces/SupplierExercise.java
```

That compiles and runs this self-contained exercise. It is not evidence that
the whole Maven project or its unfinished testing exercises passed.

The concept chapters label complete programs and their expected output.
Other code fences show signatures or fragments with their required context.
Run a complete example as its own file named after its public class.

