package es.uma.mps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
    Test cases
    1. Valid scalene triangle
    2. Valid equilateral triangle
    3. Valid isosceles triangle with all possible permutations
    4. Triangle with one of its sides with zero value
    5. Triangle with one of its sides with negative value
    6. Sum of two sides of a triangle equal to the third side with all possible permutations
    7. Sum of two sides of a triangle less than the third side with all possible permutations
    8. Triangle with all of its sides with zero value
    9. Triangle with sides as non-integer values
 */

class TriangleTest {
    Triangle triangle;

    @BeforeEach
    void setup() {
        triangle = new Triangle();
    }

    @Test
    void validScaleneTriangle() {
        assertEquals(Triangle.TriangleType.SCALENE, triangle.getType(4, 5, 3));
    }

    @Test
    void validEquilateralTriangle() {
        assertEquals(Triangle.TriangleType.EQUILATERAL, triangle.getType(5, 5, 5));
    }

    @Test
    void validIsoscelesWithPermutationsTriangle() {
        assertEquals(Triangle.TriangleType.ISOSCELES, triangle.getType(3, 3, 4));
        assertEquals(Triangle.TriangleType.ISOSCELES, triangle.getType(3, 4, 3));
        assertEquals(Triangle.TriangleType.ISOSCELES, triangle.getType(4, 3, 3));
    }

    @Test
    void oneSideZeroTriangle() {
        assertThrows(RuntimeException.class, () -> triangle.getType(0, 5, 5));
        assertThrows(RuntimeException.class, () -> triangle.getType(5, 0, 5));
        assertThrows(RuntimeException.class, () -> triangle.getType(5, 5, 0));
    }

    @Test
    void oneSideNegativeTriangle() {
        assertThrows(RuntimeException.class, () -> triangle.getType(-1, 5, 5));
        assertThrows(RuntimeException.class, () -> triangle.getType(5, -1, 5));
        assertThrows(RuntimeException.class, () -> triangle.getType(5, 5, -1));
    }

    @Test
    void twoSidesSumEqualToThirdSideWithPermutationsTriangle() {
        assertThrows(RuntimeException.class, () -> triangle.getType(1, 2, 3));
        assertThrows(RuntimeException.class, () -> triangle.getType(2, 3, 1));
        assertThrows(RuntimeException.class, () -> triangle.getType(3, 2, 1));
    }

    @Test
    void twoSidesSumLessThanThirdSideTriangle() {
        assertThrows(RuntimeException.class, () -> triangle.getType(1, 2, 4));
        assertThrows(RuntimeException.class, () -> triangle.getType(2, 4, 1));
        assertThrows(RuntimeException.class, () -> triangle.getType(4, 1, 2));
    }

    @Test
    void allSidesZeroTriangle() {
        assertThrows(RuntimeException.class, () -> triangle.getType(0, 0, 0));
    }

    @Test
    void nonIntegerValuesSidesTriangle() {
        assertEquals(Triangle.TriangleType.SCALENE, triangle.getType(2.5, 3.5, 5.5));
        assertEquals(Triangle.TriangleType.EQUILATERAL, triangle.getType(3.5, 3.5, 3.5));
        assertEquals(Triangle.TriangleType.ISOSCELES, triangle.getType(3.5, 3.5, 5.5));
    }
}