package org.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public static ValidationResult validate(String username, String email, String password, String dob) {
        ValidationResult result = new ValidationResult();

        // Username validation
        if (username == null || username.length() < 4) {
            result.addFailure("Username: min 4 characters required");
        }

        // Email validation
        if (email == null || !isValidEmail(email)) {
            result.addFailure("Email: not a valid email address");
        }

        // Password validation
        if (password == null || !isStrongPassword(password)) {
            result.addFailure("Password: not a strong password");
        }

        // Date of Birth validation
        if (dob == null || !isValidDOB(dob)) {
            result.addFailure("Date of Birth: should be 16 years or greater");
        }

        return result;
    }
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        // regular expression pattern for a valid email address
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

        // Compiling the pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Match the email against the pattern
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

public static boolean isStrongPassword(String password) {
    if (password == null || password.length() < 8) {
        return false;
    }

    // Check for at least 1 uppercase letter
    if (!password.matches(".*[A-Z].*")) {
        return false;
    }

    // Check for at least 1 special character
    if (!password.matches(".*[!@#$%^&*].*")) {
        return false;
    }

    // Check for at least 1 digit
    if (!password.matches(".*\\d.*")) {
        return false;
    }

    return true;
}

public static boolean isValidDOB(String dob) {
    if (dob == null) {
        return false;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);

    try {
        Date dateOfBirth = dateFormat.parse(dob);
        Date currentDate = new Date();

        long ageInMillis = currentDate.getTime() - dateOfBirth.getTime();
        long years = ageInMillis / 1000 / 60 / 60 / 24 / 365;

        return years >= 16;
    } catch (ParseException e) {
        // Date format is invalid
        return false;
    }
}
}
