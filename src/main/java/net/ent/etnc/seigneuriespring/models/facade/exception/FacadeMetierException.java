package net.ent.etnc.seigneuriespring.models.facade.exception;

public class FacadeMetierException extends Exception {
    public FacadeMetierException(String message) {
        super(message);
    }

    public FacadeMetierException(String message, Throwable cause) {
        super(message, cause);
    }
}
