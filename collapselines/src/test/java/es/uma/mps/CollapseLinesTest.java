package es.uma.mps;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static es.uma.mps.CollapseLines.collapseNewLines;
import static org.junit.jupiter.api.Assertions.*;

/*
    Test cases:
    1. Test that an empty string message will throw an IndexOutOfBoundsException
        0 - 1 - 2
    2. Test that a message with a single line break will collapse to an empty string
        0 - 1 - 3 - 4 - 5 - 6 - 8 - 4 - 9
    3. Test that a message string with no line breaks will not change
        0 - 1 - 3 - 4 - 5 - 7 - 8 - 4 - 9
    4. Test that a message string with a line break at the beginning will collapse to the remaining text
        0 - 1 - 3 - 4 - 5 - 6 - 8 - 4 - 5 - 7 - 8 - 4 - 9
    5. Test that a message string with two consecutive line breaks will collapse to an empty string
        0 - 1 - 3 - 4 - 5 - 6 - 8 - 4 - 5 - 6 - 8 - 4 - 9
    6. Test that an input string with a line break at the end will not change
        0 - 1 - 3 - 4 - 5 - 7 - 8 - 4 - 5 - 7 - 8 - 4 - 5 – 6 - 7 - 8 - 4 - 9
    7. Test that an input string with a line break at the end will collapse to one
        0 - 1 - 3 - 4 - 5 - 7 - 8 - 4 - 5 - 7 - 8 - 4 - 5 – 6 - 7 - 8 - 4 - 5 - 6 - 8 - 4 - 9
    8. Test that an input string with two consecutive line breaks at the beginning will collapse to the remaining text
        0 - 1 - 3 - 4 - 5 - 6 - 8 - 4 - 5 - 6 - 8 - 4 - 5 - 7 - 8 - 4 - 9
 */

class CollapseLinesTest {
    @Test
    @DisplayName("Empty string message throws IndexOutOfBoundsException")
    void emptyMessage() {
        assertThrows(IndexOutOfBoundsException.class, () -> collapseNewLines(""));
    }

    @Test
    @DisplayName("Single line break message should collapse to an empty string")
    void singleLineBreakMessage() {
        String expectedValue = "";
        String actualValue = "\n";
        assertEquals(expectedValue, collapseNewLines(actualValue));
    }

    @Test
    @DisplayName("Message with no line breaks should not change")
    void noLineBreakMessage() {
        String expectedValue = "A";
        String actualValue = "A";
        assertEquals(expectedValue, collapseNewLines(actualValue));
    }

    @Test
    @DisplayName("Message with line break at beginning should collapse to remaining text")
    void lineBreakAtBeginningMessage() {
        String expectedValue = "A";
        String actualValue = "\nA";
        assertEquals(expectedValue, collapseNewLines(actualValue));
    }

    @Test
    @DisplayName("Message with two consecutive line breaks should collapse to an empty string")
    void twoConsecutiveLineBreaksMessage() {
        String expectedValue = "";
        String actualValue = "\n\n";
        assertEquals(expectedValue, collapseNewLines(actualValue));
    }

    @Test
    @DisplayName("Message with line break at end should not change")
    void lineBreakAtEndMessage() {
        String expectedValue = "AB\n";
        String actualValue = "AB\n";
        assertEquals(expectedValue, collapseNewLines(actualValue));
    }

    @Test
    @DisplayName("Message with two line breaks at end should collapse to one")
    void twoLineBreaksAtEndMessage() {
        String expectedValue = "AB\n";
        String actualValue = "AB\n\n";
        assertEquals(expectedValue, collapseNewLines(actualValue));
    }

    @Test
    @DisplayName("Message with two consecutive line breaks at beginning should collapse to remaining text")
    void twoConsecutiveLineBreaksAtBeginningMessage() {
        String expectedValue = "A";
        String actualValue = "\n\nA";
        assertEquals(expectedValue, collapseNewLines(actualValue));
    }
}