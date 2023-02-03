package es.uma.mps;

public class Factorial {
    public int compute(int number) {
        int result;

        if (number < 0) {
            throw new RuntimeException("The value " + number + " is negative");
        } else if (number == 0) {
            result = 1;
        } else {
            result = number * compute(number - 1);
        }

        return result;
    }
}
