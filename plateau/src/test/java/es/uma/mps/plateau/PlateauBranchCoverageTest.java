package es.uma.mps.plateau;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static es.uma.mps.plateau.Plateau.longestPlateau;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Óscar Fernández Díaz
 */

@Tag("BranchCoverage")
class PlateauBranchCoverageTest {
    @Test
    public void throwExceptionWithNullArray() {
        int[] xs = null;

        assertThrows(IllegalArgumentException.class, () -> longestPlateau(xs));
    }

    @Test
    public void plateauInPositionTwoAndWithFourOfLength() {
        int[] xs = {0, 1, 3, 3, 3, 3, 2, 1};
        int expectedPosition = 2;
        int expectedLength = 4;

        Plateau.Pair result = longestPlateau(xs);

        assertEquals(expectedPosition, result.position());
        assertEquals(expectedLength, result.length());
    }
}