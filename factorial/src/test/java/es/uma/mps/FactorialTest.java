package es.uma.mps;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/*
    Test cases
    1. Factorial 0 -> 1
    2. Factorial 1 -> 1
    3. Factorial 2 -> 2
    4. Factorial 3 -> 6
    5. Factorial 4 -> 24
    6. Factorial 5 -> 120
 */

class FactorialTest {
    Factorial factorial;
    @BeforeEach
    void setup() {
        factorial = new Factorial();
    }

    @AfterEach
    void shutdown() {
        factorial = null;
    }

    @Nested
    class SmallNaturals {
        @Test
        void factorialOfZeroIsOne() {
            int obtainedValue = factorial.compute(0);
            int expectedValue = 1;

            assertEquals(expectedValue, obtainedValue);
        }

        @Test
        void factorialOfOneIsOne() {
            int obtainedValue = factorial.compute(1);
            int expectedValue = 1;

            assertEquals(expectedValue, obtainedValue);
        }

        @Test
        void factorialOfTwoIsTwo() {
            int obtainedValue = factorial.compute(2);
            int expectedValue = 2;

            assertEquals(expectedValue, obtainedValue);
        }

        @Test
        void factorialOfThreeIsSix() {
            int obtainedValue = factorial.compute(3);
            int expectedValue = 6;

            assertEquals(expectedValue, obtainedValue);
        }

        @Test
        void factorialOfFourIsTwentyFour() {
            int obtainedValue = factorial.compute(4);
            int expectedValue = 24;

            assertAll("factorial",
                    () -> assertNotNull(factorial),
                    () -> assertEquals(expectedValue, obtainedValue),
                    () -> assertNotEquals(0, obtainedValue),
                    () -> assertTrue(obtainedValue > 0),
                    () -> assertFalse(obtainedValue < 0)
            );
        }

        @Test
        void factorialOfFiveIs120() {
            int obtainedValue = factorial.compute(5);
            int expectedValue = 120;

            assertEquals(expectedValue, obtainedValue);
        }
    }

    @Test
    void factorialOfMinusOneIs() {
        assertThrows(RuntimeException.class, () -> factorial.compute(-1));
    }
}