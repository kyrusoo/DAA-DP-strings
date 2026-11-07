package org.example.edit;

import org.example.metrics.OperationMetrics;

import java.util.Objects;

/** Direct recursion without memoization; intended for short strings. */
public final class RecursiveEditDistance implements EditDistance {
    @Override
    public int distance(String a, String b, OperationMetrics metrics) {
        Objects.requireNonNull(a, "First string must not be null");
        Objects.requireNonNull(b, "Second string must not be null");
        Objects.requireNonNull(metrics, "Metrics must not be null");
        return distance(a, b, a.length(), b.length(), metrics);
    }

    // State: D(i, j) is the distance between prefixes a[0..i) and b[0..j).
    // Transition: equal last characters -> D(i-1, j-1); otherwise 1 + min(delete, insert, replace).
    // Base cases: D(0, j) = j and D(i, 0) = i.
    private int distance(String a, String b, int i, int j, OperationMetrics metrics) {
        metrics.recordRecursiveCall();
        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }

        metrics.recordCharacterComparison();
        if (a.charAt(i - 1) == b.charAt(j - 1)) {
            return distance(a, b, i - 1, j - 1, metrics);
        }

        int delete = distance(a, b, i - 1, j, metrics);
        int insert = distance(a, b, i, j - 1, metrics);
        int replace = distance(a, b, i - 1, j - 1, metrics);
        return 1 + Math.min(delete, Math.min(insert, replace));
    }
}
