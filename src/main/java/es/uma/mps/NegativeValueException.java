package es.uma.mps;

public class NegativeValueException extends RuntimeException {
    public NegativeValueException(String errorMessage) {
        super(errorMessage);
    }
}
