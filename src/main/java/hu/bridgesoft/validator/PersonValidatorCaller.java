package hu.bridgesoft.validator;

import hu.bridgesoft.validator.demovalidator.CustomValidationException;
import hu.bridgesoft.validator.demovalidator.DefaultPersonValidator;
import hu.bridgesoft.validator.demovalidator.PersonValidator;
import hu.bridgesoft.validator.demovalidator.dto.Address;
import hu.bridgesoft.validator.demovalidator.dto.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonValidatorCaller {

    public static final Logger LOGGER = LoggerFactory.getLogger(PersonValidatorCaller.class);

    public static void main(String[] args) {
        Person person = createPerson();
        person.setFirstName("Laszlo");
        person.setLastName("Kasler");
        person.getAdress().setCountry("Hungary");
        person.getAdress().setZipCode(1000);
        person.getAdress().setStreet("Kossuth");
        person.getAdress().setSettlement("Budapest");
        Person personSecond = createPerson();
        personSecond.setLastName("Kasler");

        PersonValidator personValidator = new DefaultPersonValidator();
        validatePerson(person, personValidator);
        validatePerson(personSecond, personValidator);
    }

    private static void validatePerson(Person person, PersonValidator personValidator) {
        try {
            personValidator.validate(person);
        } catch (CustomValidationException e) {
            LOGGER.error("Validation failed with person: {}", person, e);
        }
    }

    private static Person createPerson() {
        Person person = new Person();
        person.setAdress(new Address());
        return person;
    }
}
