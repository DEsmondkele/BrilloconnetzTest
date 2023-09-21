package org.example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

  
    @Test
    public void testValidate() {
        // Test valid input
        ValidationResult result = InputValidator.validate("JohnDoe", "john.doe@example.com", "P@ssw0rd", "2000-01-01");
        assertTrue(result.isValid());

        // Test invalid username (less than 4 characters)
        result = InputValidator.validate("Joe", "joe@example.com", "P@ssw0rd", "2000-01-01");
        assertFalse(result.isValid());
        assertTrue(result.getFailures().contains("Username: min 4 characters required"));

        // Test invalid email format
        result = InputValidator.validate("Alice", "invalidemail", "P@ssw0rd", "2000-01-01");
        assertFalse(result.isValid());
        assertTrue(result.getFailures().contains("Email: not a valid email address"));

        // Test for invalid password (not strong)
        result = InputValidator.validate("Bob", "bob@example.com", "weak", "2000-01-01");
        assertFalse(result.isValid());
        assertTrue(result.getFailures().contains("Password: not a strong password"));

        // Test for invalid date of birth (under 16 years old)
        result = InputValidator.validate("Eve", "eve@example.com", "P@ssw0rd", "2010-01-01");
        assertFalse(result.isValid());
        assertTrue(result.getFailures().contains("Date of Birth: should be 16 years or greater"));

        // Test null input
        result = InputValidator.validate(null, null, null, null);
        assertFalse(result.isValid());
        assertTrue(result.getFailures().contains("Username: min 4 characters required"));
        assertTrue(result.getFailures().contains("Email: not a valid email address"));
        assertTrue(result.getFailures().contains("Password: not a strong password"));
        assertTrue(result.getFailures().contains("Date of Birth: should be 16 years or greater"));
    }

    @Test
    public void testIsValidEmail() {
        assertTrue(InputValidator.isValidEmail("test@example.com"));
        assertFalse(InputValidator.isValidEmail("invalidemail"));
        assertFalse(InputValidator.isValidEmail(null));
    }

    @Test
    public void testIsStrongPassword() {
        assertTrue(InputValidator.isStrongPassword("P@ssw0rd"));
        assertFalse(InputValidator.isStrongPassword("weak"));
        assertFalse(InputValidator.isStrongPassword(null));
    }

    @Test
    public void testIsValidDOB() {
        assertTrue(InputValidator.isValidDOB("2000-01-01"));
        assertFalse(InputValidator.isValidDOB("2010-01-01"));
        assertFalse(InputValidator.isValidDOB(null));
        assertFalse(InputValidator.isValidDOB("invalid-date"));
    }
}