package es.uma.mps;

public class Factorial {
    public int compute(int number) {
        int result = 0;

        if (number == 0) {
            result = 1;
        } else if (number >= 1) {
            result = number * compute(number - 1);
        }

        return result;
    }
}
