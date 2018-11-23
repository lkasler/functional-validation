package hu.bridgesoft.validator;

import hu.bridgesoft.validator.api.DefaultValidation;
import hu.bridgesoft.validator.demovalidator.CustomValidationException;
import hu.bridgesoft.validator.demovalidator.DefaultPersonValidator;
import hu.bridgesoft.validator.demovalidator.PersonValidator;
import hu.bridgesoft.validator.demovalidator.dto.Address;
import hu.bridgesoft.validator.demovalidator.dto.Person;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test of DefaultPersonValidator.
 */
public class DefaultPersonValidatorTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultPersonValidatorTest.class);

    private PersonValidator personValidator = new DefaultPersonValidator();

    @Test
    public void testValidationSuccess() throws IOException, CustomValidationException {
        Person person = new Person();
        person.setAdress(new Address());
        person.setFirstName("Laszlo");
        person.setLastName("Kasler");
        person.getAdress().setCountry("Hungary");
        person.getAdress().setZipCode(1000);
        person.getAdress().setStreet("Kossuth");
        person.getAdress().setSettlement("Budapest");
        personValidator.validate(person);
    }

    @Test
    public void testValidationFail() throws IOException, CustomValidationException {
        Person person = new Person();
        person.setAdress(new Address());
        person.setFirstName("Laszlo");
        person.setLastName("K");
        person.getAdress().setCountry("Hungary");
        person.getAdress().setZipCode(1000);
        person.getAdress().setStreet("Kossuth");
        person.getAdress().setSettlement("Budapest");
        assertThrows(CustomValidationException.class, () -> personValidator.validate(person));
    }

    @Test
    public void testValidationoOntTheFlySuccess()  {
        Person person = new Person();
        person.setAdress(new Address());
        person.setFirstName("Laszlo");
        person.setLastName("KA");
        person.getAdress().setCountry("Hungary");
        person.getAdress().setZipCode(1000);
        person.getAdress().setStreet("Kossuth");
        person.getAdress().setSettlement("Budapest");
        DefaultValidation.<String>fromPredicate(s -> s.length() > 1).and(s -> s.startsWith("K")).validate(person.getLastName())
                .throwException(new IllegalStateException("This shouldn't happen"));
    }

    @Test
    public void testValidationoOntTheFlyFail()  {
        Person person = new Person();
        person.setAdress(new Address());
        person.setFirstName("Laszlo");
        person.setLastName("KA");
        person.getAdress().setCountry("Hungary");
        person.getAdress().setZipCode(1000);
        person.getAdress().setStreet("Kossuth");
        person.getAdress().setSettlement("Budapest");
        Optional<String> reason = DefaultValidation.<String>fromPredicate(s -> s.length() > 2).and(s -> s.startsWith("K")).validate(person.getLastName())
                .getReason("LastName is shorter then 3 or doesn't start with 'K'");
        assertEquals("LastName is shorter then 3 or doesn't start with 'K'", reason.get());
    }

}
