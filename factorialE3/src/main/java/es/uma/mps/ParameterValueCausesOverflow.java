package es.uma.mps;

/**
 * @author Antonio J. Nebro
 */
public class ParameterValueCausesOverflow extends RuntimeException {

    /**
     * Exception thrown when the value of the parameter of the {@link Factorial#compute} method is too
     * high and can cause overflow, thus giving an unexpected result.
     *
     * @param errorMessage
     */
    public ParameterValueCausesOverflow(String errorMessage) {
        super(errorMessage);
    }
}
