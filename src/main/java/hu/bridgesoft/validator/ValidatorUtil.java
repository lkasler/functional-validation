package hu.bridgesoft.validator;

import hu.bridgesoft.validator.api.DefaultValidation;
import hu.bridgesoft.validator.api.Validation;

import java.util.Collection;

/**
 * ValidationUtil to provide common Validation's.
 */
public class ValidatorUtil {

    private ValidatorUtil() {
    }

    public static final Validation<String> NOT_NULL_NOT_EMPTY_STRING = DefaultValidation.fromPredicate(string -> string != null && !string.isEmpty());

    public static final Validation<Collection<?>> NOT_NULL_NOT_EMPTY_COLLECTION = DefaultValidation.fromPredicate(collection -> collection != null && !collection.isEmpty());

    public static final Validation<Object> NOT_NULL_OBJECT = DefaultValidation.fromPredicate(object -> object != null);


    public static final Validation<String> startWithString(String start) {
        return DefaultValidation.fromPredicate(s -> s.startsWith(start));
    }

    public static final Validation<String> sizeOfString(int size) {
        return DefaultValidation.fromPredicate(s -> s.length() == size);
    }


    public static final Validation<String> stringEquals(String equal) {
        return DefaultValidation.fromPredicate(s -> s.equals(equal));
    }

}
