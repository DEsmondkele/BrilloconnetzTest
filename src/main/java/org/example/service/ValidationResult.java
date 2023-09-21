package org.example.service;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private List<String> failures = new ArrayList<>();

    public void addFailure(String message) {
        failures.add(message);
    }

    public boolean isValid() {
        return failures.isEmpty();
    }

    public List<String> getFailures() {
        return failures;
    }
}
