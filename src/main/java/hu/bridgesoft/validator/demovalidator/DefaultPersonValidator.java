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
