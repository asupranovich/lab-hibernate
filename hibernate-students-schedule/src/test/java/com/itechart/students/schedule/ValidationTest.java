package com.itechart.students.schedule;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.itechart.students.schedule.model.Address;
import com.itechart.students.schedule.model.AddressType;

@Ignore
public class ValidationTest {

    private Validator validator;

    @Before
    public void before() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testNotNullValidation() {
        Address address = new Address();
        address.setCity("New York");
        address.setState("NY");
        address.setStreet("Main str 55");
        address.setZip("10001");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        displayViolations(violations);
    }

    @Test
    public void testLengthValidation() {
        Address address = new Address();
        address.setCity("New York");
        address.setState("NY");
        address.setStreet("Ma");
        address.setZip("10001");
        address.setType(AddressType.HOME);

        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        displayViolations(violations);
    }

    private void displayViolations(Set<ConstraintViolation<Address>> violations) {
        if (violations == null || violations.isEmpty()) {
            System.err.println("No violations found!");
            return;
        }

        violations.forEach((violation) -> {
            System.err.println("Violation: " + violation.getMessage());
        });
    }

}
