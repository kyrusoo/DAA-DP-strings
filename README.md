# DAA Assignment 4: Dynamic Programming and String Algorithms

Java 21 Maven project for three Edit Distance implementations and Naive and KMP string search.

## Build and test

Requirements: JDK 21 or newer and Maven 3.9 or newer.

```text
mvn clean test
```

In IntelliJ IDEA, select JDK 21 or newer for the project and Maven runner, reload `pom.xml`, and run the Maven `test` lifecycle goal.

Implemented so far: recursive Edit Distance, operation counters, and JUnit 5 tests for the reference distances, empty strings, individual edits, and counter behavior. The full-table and two-row versions and string-search algorithms will follow in later milestones.

## Recursive Edit Distance

```java
EditDistance algorithm = new RecursiveEditDistance();
int answer = algorithm.distance("kitten", "sitting"); // 3

OperationMetrics metrics = new OperationMetrics();
int measuredAnswer = algorithm.distance("kitten", "sitting", metrics);
long calls = metrics.recursiveCalls();
long comparisons = metrics.characterComparisons();
```

These types are in `org.example.edit` and `org.example.metrics`. The two-argument method returns just the distance. The overload accepts counters that accumulate until `metrics.reset()` is called. Every recursive state evaluation, including a base case, counts as one call; each comparison of the last characters of two nonempty prefixes counts once. Null strings or a null metrics object throw `NullPointerException`.

The recursive version has no memoization. It works with prefix lengths without allocating substrings, follows one branch for matching characters, and explores deletion, insertion, and replacement for mismatches. Keep inputs short: its worst-case running time is exponential, and deep recursion can exhaust the stack.

## Project layout

- `src/main/java/org/example/edit/`: Edit Distance implementations.
- `src/main/java/org/example/search/`: Naive search, KMP, and LPS construction.
- `src/main/java/org/example/metrics/`: counters recorded inside algorithms.
- `src/main/java/org/example/experiment/`: fixed-seed generators, runner, and plot generation.
- `src/test/java/org/example/`: JUnit 5 tests.
- `results/results.csv`: experiment output, to be generated later.
- `results/plots/`: PNG charts, to be generated later.
- `REPORT.md`: Experiment Design Card, followed later by results and analysis.

The experiment command will be documented when the runner is implemented. No benchmark has been run for this milestone.

## Git workflow

Work on `main` in small, working commits. Tag the final verified version `v1.0`.

## AI acknowledgement

AI assistance was used to prepare the Maven configuration, project structure, and experiment plan, and to implement and test recursive Edit Distance and its operation counters. This acknowledgement will be updated to describe any further assistance used.
