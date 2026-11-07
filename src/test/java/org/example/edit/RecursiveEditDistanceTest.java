package org.example.edit;

import org.example.metrics.OperationMetrics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecursiveEditDistanceTest {
    private final EditDistance algorithm = new RecursiveEditDistance();

    static Stream<Arguments> distanceCases() {
        return Stream.of(
                Arguments.of("kitten", "sitting", 3),
                Arguments.of("flaw", "lawn", 2),
                Arguments.of("intention", "execution", 5),
                Arguments.of("", "abc", 3),
                Arguments.of("abc", "abc", 0),
                Arguments.of("", "", 0),
                Arguments.of("abc", "", 3),
                Arguments.of("ab", "a", 1),
                Arguments.of("a", "ab", 1),
                Arguments.of("cat", "cut", 1),
                Arguments.of("ab", "ba", 2),
                Arguments.of("aaaa", "aa", 2)
        );
    }

    @ParameterizedTest
    @MethodSource("distanceCases")
    void returnsExpectedDistance(String a, String b, int expected) {
        assertEquals(expected, algorithm.distance(a, b));
    }

    @Test
    void countsEmptyInputAsOneCallWithoutCharacterComparisons() {
        OperationMetrics metrics = new OperationMetrics();

        assertEquals(3, algorithm.distance("", "abc", metrics));

        assertEquals(1, metrics.recursiveCalls());
        assertEquals(0, metrics.characterComparisons());
    }

    @Test
    void matchingCharactersFollowOnlyTheDiagonal() {
        OperationMetrics metrics = new OperationMetrics();

        assertEquals(0, algorithm.distance("abc", "abc", metrics));

        assertEquals(4, metrics.recursiveCalls());
        assertEquals(3, metrics.characterComparisons());
    }

    @Test
    void mismatchExploresAllThreeEdits() {
        OperationMetrics metrics = new OperationMetrics();

        assertEquals(1, algorithm.distance("a", "b", metrics));

        assertEquals(4, metrics.recursiveCalls());
        assertEquals(1, metrics.characterComparisons());
    }

    @Test
    void countersAccumulateUntilExplicitlyReset() {
        OperationMetrics metrics = new OperationMetrics();
        algorithm.distance("a", "b", metrics);
        algorithm.distance("a", "a", metrics);

        assertEquals(6, metrics.recursiveCalls());
        assertEquals(2, metrics.characterComparisons());

        metrics.reset();
        assertEquals(0, metrics.recursiveCalls());
        assertEquals(0, metrics.characterComparisons());

        algorithm.distance("", "", metrics);
        assertEquals(1, metrics.recursiveCalls());
        assertEquals(0, metrics.characterComparisons());
    }

    @Test
    void rejectsNullArguments() {
        OperationMetrics metrics = new OperationMetrics();

        assertThrows(NullPointerException.class, () -> algorithm.distance(null, "abc", metrics));
        assertThrows(NullPointerException.class, () -> algorithm.distance("abc", null, metrics));
        assertThrows(NullPointerException.class, () -> algorithm.distance("abc", "abc", null));
        assertEquals(0, metrics.recursiveCalls());
        assertEquals(0, metrics.characterComparisons());
    }
}
