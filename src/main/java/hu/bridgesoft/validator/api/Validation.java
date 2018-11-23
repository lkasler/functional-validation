package hu.bridgesoft.validator.api;

import java.util.function.Predicate;

/**
 * Validation interface for business validations.
 */
public interface Validation<TYPE_TO_VALIDATE> {

    /**
     * Use type to be checked for validator.
     *
     * @param param
     * @return
     */
    ValidationResult validate(TYPE_TO_VALIDATE param);

    /**
     * And precdicate with current predicate.
     * @param predicate
     * @return Validation
     */
    Validation and(Predicate<TYPE_TO_VALIDATE> predicate);

    /**
     * Or predicate with current predicate
     * @param predicate
     * @return
     */
    Validation or(Predicate<TYPE_TO_VALIDATE> predicate);

    /**
     * Negate current predicate.
     * @return
     */
    Validation negate();
}