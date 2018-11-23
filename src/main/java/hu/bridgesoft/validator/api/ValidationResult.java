package hu.bridgesoft.validator.api;

import java.util.Optional;

/**
 * Validation result to hold a validator result.
 * Validation result can be work in Exception mode throwing Exception in case of validator error see throwException
 * and validator as a string result when you specify field name which caused the validator.
 * @author u95598
 */
public class ValidationResult {

    private boolean valid;

    public boolean isValid() {
        return valid;
    }

    public static ValidationResult ok() {
        return new ValidationResult(true);
    }

    private ValidationResult(boolean valid) {
        this.valid = valid;
    }

    public static ValidationResult fail() {
        return new ValidationResult(false);
    }

    /**
     *
     * @param field
     * @return
     */
    public Optional<String> getReason(String field) {
        return this.valid ? Optional.empty() : Optional.of(field != null ? field : "Field occured validator error but not specified");
    }

    /**
     * If Predicate validate fail and InvalidPaymentException is present will throw the InvalidPaymentException else return false.
     * @param validationException Validation Exception instance for ckecked Exception
     * @param <VALIDATION_EXCEPTION> type of Checked exception.
     * @return if valid return true else throw chcked exception.
     * @throws VALIDATION_EXCEPTION
     */
    public <VALIDATION_EXCEPTION extends Exception> boolean throwException(VALIDATION_EXCEPTION validationException) throws VALIDATION_EXCEPTION {
        if (!this.valid) {
            throw validationException;
        }
        return true;
    }

}
