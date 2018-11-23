# Java 8 Predicate based Validator
 

## Validator Interface

```java
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
```

## Implementation of Validation

```java
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

```

### Examples of Validator usage

#### Create predicator validator on the fly throwing checked exception if validation failes

```java
DefaultValidation.<String>fromPredicate(s -> s.length() > 1).and(s -> s.startsWith("K")).validate(person.getLastName())
                .throwException(new IllegalStateException("This shouldn't happen"));
``` 

Create predicator validator on the fly returning String of the root cause

```java
DefaultValidation.<String>fromPredicate(s -> s.length() > 1).and(s -> s.startsWith("K")).validate(person.getLastName())
                .throwException(new IllegalStateException("This shouldn't happen"));
``` 


Create static reusable validations

```java
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

```

### Example validating a Person

```java
package hu.bridgesoft.validator.demovalidator;

import hu.bridgesoft.validator.demovalidator.dto.Person;

public interface PersonValidator {

    void validate(Person person) throws CustomValidationException;

}
```

```java
package hu.bridgesoft.validator.demovalidator;

import hu.bridgesoft.validator.ValidatorUtil;
import hu.bridgesoft.validator.demovalidator.dto.Person;

public class DefaultPersonValidator implements PersonValidator {

    @Override
    public void validate(Person person) throws CustomValidationException {
        ValidatorUtil.NOT_NULL_OBJECT.validate(person).throwException(new CustomValidationException("Person cannot be null!"));
        ValidatorUtil.NOT_NULL_NOT_EMPTY_STRING.validate(person.getFirstName()).throwException(new CustomValidationException("FirstName cannot be null or empty!"));
        ValidatorUtil.NOT_NULL_NOT_EMPTY_STRING.and(string -> string.length() > 1).validate(person.getLastName()).throwException(new CustomValidationException("LastName cannot be null or shorten than 2 characters!"));
        ValidatorUtil.NOT_NULL_OBJECT.validate(person.getAdress()).throwException(new CustomValidationException("Address cannot be null!"));
    }
}

```