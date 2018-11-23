package hu.bridgesoft.validator.demovalidator;

public class CustomValidationException extends Exception {

    public CustomValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomValidationException(String message) {
        super(message);
    }
}
