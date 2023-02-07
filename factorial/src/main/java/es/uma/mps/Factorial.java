package es.uma.mps;

/*
 * Class providing a method to compute the factorial of an integer number
 * @author Óscar Fernández Díaz
 */

public class Factorial {
    public int compute(int number) {
        int result;

        if (number < 0) {
            throw new NegativeValueException("The value " + number + " is negative");
        } else if (number == 0) {
            result = 1;
        } else {
            result = number * compute(number - 1);
        }

        return result;
    }

    void f() {
        // aaaa
    }
}
