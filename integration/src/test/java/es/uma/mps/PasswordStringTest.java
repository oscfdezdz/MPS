package es.uma.mps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordStringTest {

    @Test
    public void validateReturnsTrueIfThePasswordIsCorrect() {
        assertTrue(new PasswordString("hello.123;").validate());
    }

    @Test
    public void validateReturnsFalseIfThePasswordIsTooShort() {
        assertFalse(new PasswordString("hello").validate());
    }

    @Test
    public void validateReturnsFalseIfThePasswordIsTooLong() {
        assertFalse(new PasswordString("1234.1234.1234.1345.1").validate());
    }

    @Test
    public void validateReturnsFalseIfThePasswordDoestNotContainASpecialChar() {
        assertFalse(new PasswordString("Hell0World").validate());
    }

    @Test
    public void validateReturnsFalseIfThePasswordDoestNotContainADigit() {
        assertFalse(new PasswordString("Hello.World").validate());
    }
}
