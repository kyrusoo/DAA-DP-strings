package org.example.edit;

import org.example.metrics.OperationMetrics;

/** Common API for the three Edit Distance implementations. */
public interface EditDistance {
    default int distance(String a, String b) {
        return distance(a, b, new OperationMetrics());
    }

    /** Adds this invocation's work to the supplied counters without resetting them. */
    int distance(String a, String b, OperationMetrics metrics);
}
