package es.uma.mps;

public class EmptyBoundedQueueException extends RuntimeException {

    public EmptyBoundedQueueException(String msg) {
        super(msg);
    }
}
