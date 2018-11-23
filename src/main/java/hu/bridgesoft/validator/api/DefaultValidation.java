package hu.bridgesoft.validator.api;

import java.util.function.Predicate;

/**
 * Default validator with Predicate.
 */
public class DefaultValidation<TYPE_TO_VALIDATE> implements Validation<TYPE_TO_VALIDATE> {

    private Predicate<TYPE_TO_VALIDATE> predicate;

    public static <TYPE_TO_VALIDATE> DefaultValidation<TYPE_TO_VALIDATE> fromPredicate(Predicate<TYPE_TO_VALIDATE> predicate) {
        return new DefaultValidation<TYPE_TO_VALIDATE>(predicate);
    }

    private DefaultValidation(Predicate<TYPE_TO_VALIDATE> predicate) {
        this.predicate = predicate;
    }


    @Override
    public ValidationResult validate(TYPE_TO_VALIDATE param) {
            return predicate.test(param) ? ValidationResult.ok() : ValidationResult.fail();
    }

    @Override
    public DefaultValidation and(Predicate<TYPE_TO_VALIDATE>  predicate) {
        this.predicate = this.predicate.and(predicate);
        return this;
    }

    @Override
    public DefaultValidation or(Predicate<TYPE_TO_VALIDATE>  predicate) {
        this.predicate = this.predicate.or(predicate);
        return this;
    }

    @Override
    public DefaultValidation negate() {
        this.predicate = this.predicate.negate();
        return this;
    }
    
}
