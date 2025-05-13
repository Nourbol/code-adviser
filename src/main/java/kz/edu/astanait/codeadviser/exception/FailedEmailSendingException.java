package kz.edu.astanait.codeadviser.exception;

public class FailedEmailSendingException extends RuntimeException {

    public FailedEmailSendingException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
