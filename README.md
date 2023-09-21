# BrilloconnetzTest
# A User Validation and JWT Generation Java Application

This Java application is designed to perform user input validations concurrently and generate JSON Web Tokens (JWT) for authenticated users. The application ensures that user-provided data meets specific validation criteria, and if successful, it generates a JWT for further authentication.

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Apache Maven

## Installation

1. Clone the repository to your local machine:

   ```shell
   git clone  https://github.com/DEsmondkele/BrilloconnetzTest.git
   Build the project using Maven:

cd your-repo

 `mvn clean install`

## Usage
 Running the Application
 To run the application, use the following command:

`java -jar target/BrilloconnetzTest-1.0-SNAPSHOT.jar`

## Input Fields
 The application prompts for the following user input fields:

- Username (Validation: Not empty, minimum 4 characters)
- Email (Validation: Not empty, valid email address)
- Password (Validation: Not empty, strong password with at least 1 upper case, 1 special character, 1 number, minimum 8 characters)
- Date of Birth (Validation: Not empty, should be 16 years or greater)

## Validation and JWT Generation

 The application performs concurrent validation of the provided user data.

If all validation checks pass, the application generates a signed JWT.

If any validation fails, the application returns a message specifying which validation requirements were not met. For example:

 - "Email: not empty"
 - "Password: not a strong password"

## Token Verification
The application can also verify the generated JWT. When you choose to verify a token, it will return one of the following results:

- "Verification pass" if the token is valid and has not expired.
- "Verification fails" if the token is invalid or has expired.

