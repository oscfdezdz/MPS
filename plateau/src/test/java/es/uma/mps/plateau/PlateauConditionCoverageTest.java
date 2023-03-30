package es.uma.mps.plateau;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static es.uma.mps.plateau.Plateau.longestPlateau;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Óscar Fernández Díaz
 */

@Tag("ConditionCoverage")
class PlateauConditionCoverageTest {
    @Test
    public void throwExceptionWithNullArray() {
        int[] xs = null;

        assertThrows(IllegalArgumentException.class, () -> longestPlateau(xs));
    }

    @Test
    public void throwExceptionWithEmptyArray() {
        int[] xs = {};

        assertThrows(IllegalArgumentException.class, () -> longestPlateau(xs));
    }

    @Test
    public void noPlateau() {
        int[] xs = {1, 2, 3, 4, 5, 6};
        int expectedPosition = -1;
        int expectedLength = 0;

        Plateau.Pair result = longestPlateau(xs);

        assertEquals(expectedPosition, result.position());
        assertEquals(expectedLength, result.length());
    }

    @Test
    public void multiplePlateausOfDifferentLengths() {
        int[] xs = {1, 2, 2, 2, 1, 0, 3, 3, 1, 1};
        int expectedPosition = 1;
        int expectedLength = 3;

        Plateau.Pair result = longestPlateau(xs);

        assertEquals(expectedPosition, result.position());
        assertEquals(expectedLength, result.length());
    }

    @Test
    public void plateauAndArrayOfSameLengthAtEnd() {
        int[] xs = {1, 2, 2, 2, 1, 0, 3, 3, 3};
        int expectedPosition = 1;
        int expectedLength = 3;

        Plateau.Pair result = longestPlateau(xs);

        assertEquals(expectedPosition, result.position());
        assertEquals(expectedLength, result.length());
    }
}