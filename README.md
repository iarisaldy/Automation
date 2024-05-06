# Automation Test Suite

This project contains automated tests for both UI and API layers. It uses Java with Selenium for UI tests and RestAssured for API tests.

## Requirements

- JDK installed on your computer.
- ChromeDriver downloaded and placed in the correct path.

## Setup

1. Clone this repository to your computer.
2. Open the project in your favorite IDE.

## Running UI Tests

1. Open the `UITests.java` file in the `com.automation` package in your IDE.
2. Run the `UITests` class using TestNG to execute the UI tests.

## Running API Tests

1. Open the `APITests.java` file in the `com.automation` package in your IDE.
2. Run the `APITests` class using TestNG to execute the API tests.

## Important Note

- Make sure to change the values of `KEY`, `TOKEN`, and other information to the actual values before running the tests.

### Running UI Tests from Command Line

```bash
mvn clean test -Dtest=UITests