package org.example;

import org.example.service.InputValidator;
import org.example.service.JwtUtils;
import org.example.service.ValidationResult;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Date of Birth: ");
        String dob = scanner.nextLine();

        ValidationResult validation = InputValidator.validate(username, email, password, dob);

        if (validation.isValid()) {
            String jwt = JwtUtils.generateJwt();
            System.out.println("JWT: " + jwt);

            // Verify the JWT
            String verificationResult = JwtUtils.verifyJwt(jwt);
            System.out.println(verificationResult);
        } else {
            System.out.println("Validation failed for the following fields:");
            for (String failure : validation.getFailures()) {
                System.out.println(failure);
            }
        }

        scanner.close();
    }
}