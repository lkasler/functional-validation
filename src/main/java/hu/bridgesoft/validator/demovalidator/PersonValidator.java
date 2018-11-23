package hu.bridgesoft.validator.demovalidator;

import hu.bridgesoft.validator.demovalidator.dto.Person;

public interface PersonValidator {

    void validate(Person person) throws CustomValidationException;

}
