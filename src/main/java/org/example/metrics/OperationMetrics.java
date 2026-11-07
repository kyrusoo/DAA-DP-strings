package org.example.metrics;

/** Counts actual work inside algorithms; timers and input generation are separate. */
public final class OperationMetrics {
    private long recursiveCalls;
    private long characterComparisons;

    /** Counts a recursive state evaluation, including a base case. */
    public void recordRecursiveCall() {
        recursiveCalls++;
    }

    /** Counts a comparison between two characters, excluding bounds and loop checks. */
    public void recordCharacterComparison() {
        characterComparisons++;
    }

    public long recursiveCalls() {
        return recursiveCalls;
    }

    public long characterComparisons() {
        return characterComparisons;
    }

    public void reset() {
        recursiveCalls = 0;
        characterComparisons = 0;
    }
}
