# Java BDD Selenium Framework

Enterprise-grade automation framework built using:

* Java 17
* Maven
* Selenium 4
* Cucumber (BDD)
* JUnit 5 (JUnit Platform)
* Allure Reporting

---

## Prerequisites

Make sure the following are installed:

* Java 17 (JAVA_HOME configured)
* Maven 3.9+
* Chrome / Firefox / Edge browser
* Allure CLI (for reporting)

Install Allure (Windows):
choco install allure

---

## Project Setup

1. Clone or download the project
2. Open in VS Code / IntelliJ
3. Ensure Java 17 is configured
4. Update config if needed:

src/test/resources/config/framework-config.properties

5. Add your feature files here:

src/test/resources/features

---

## Run Tests

Run all tests:

mvn clean test

---

## Run with Overrides (Important)

You can override config without changing files:

mvn clean test -Dbrowser=firefox
mvn clean test -Dheadless=true
mvn clean test -Dbrowser=edge -Dheadless=true

---

## Allure Reports

Option 1 (Quick View):

allure serve allure-results

This generates and opens the report automatically.

Option 2 (Persistent Report):

allure generate allure-results --clean -o allure-report
allure open allure-report

---

## Framework Structure

src
├── main
│   └── java
│       └── com.enterprise.automation.framework
│           ├── actions
│           ├── config
│           ├── driver
│           ├── pages
│           ├── utils
│           └── waits
└── test
├── java
│   └── com.enterprise.automation.tests
│       ├── hooks
│       ├── runners
│       ├── stepdefinitions
│       └── utils
└── resources
├── config
├── features
└── testdata

---

## Package Overview

Framework Layer (main):

* config → Reads configuration properties
* driver → Thread-safe WebDriver management
* actions → Reusable element interactions
* pages → Page Object Model classes
* waits → Explicit wait utilities
* utils → Common reusable helpers

Test Layer (test):

* hooks → Setup & teardown (Before/After)
* stepdefinitions → Cucumber step implementations
* runners → Test execution entry point
* utils → Test-specific utilities (e.g., screenshots)

---

## Key Features

* Thread-safe WebDriver using ThreadLocal
* Clean Page Object Model design
* BDD using Cucumber (Gherkin syntax)
* Config-driven execution
* Screenshot capture on failure
* Allure reporting integration
* Scalable and maintainable structure

---

## Example Flow

1. Feature file defines scenario
2. Step definition maps steps
3. Page class handles UI logic
4. Actions perform reusable Selenium operations
5. DriverFactory manages browser lifecycle

---

## Notes

Do NOT commit these folders:
target/
allure-results/
allure-report/

Add them to .gitignore

---

## Future Enhancements

* Parallel execution support
* Logging framework (Logback)
* Environment-based config (QA/DEV/PROD)
* Retry mechanism
* CI integration (GitHub Actions / Jenkins)

---

## Author

Built as a reusable enterprise automation framework starter.
