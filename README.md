
# Selenium Testing Framework

## Overview

This project is a robust, modular, and scalable Selenium-based automation testing framework. Designed for testing modern web applications, the framework supports Allure Reporting and employs the TestNG framework for organizing and executing tests.

The project includes dynamic locator management, modularized test methods, and a clear Page Object Model (POM) architecture to ensure maintainability and scalability.

---

## Features

### 🧩 Modular Architecture

- Implements **Page Object Model (POM)** to separate test logic from UI interactions.
- Reusable utility methods for screenshots, dynamic waits, and test environment configuration.

### 📈 Reporting

- Integrated with **Allure Reporting** for detailed and visual test reports.
- Every test includes steps and descriptions for clear debugging.

### 🚀 Scalability

- Supports parallel test execution using TestNG.
- Flexible configuration via `TestNG.xml` and JSON-based locator management.

### 🌐 Cross-Browser Testing

- Configurable browser selection via `config.properties`.
- Tested with major browsers like Chrome, Firefox, and Edge.

### 📂 Dynamic Screenshot Naming

- Automatically saves screenshots with unique names based on test names and timestamps.

---

## Directory Structure

```plaintext
selenium_task_
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── org.example
│   │   │   │   ├── pages        # Page Object classes
│   │   │   │   ├── utils        # Utility classes (e.g., DriverManager, ConfigReader)
│   ├── test
│   │   ├── java
│   │   │   ├── tests            # Test classes
│   ├── resources
│   │   ├── TestNG.xml           # TestNG configuration file
├── screenshots                  # Automatically generated test screenshots
├── target                       # Test output directory
├── pom.xml                      # Maven project file with dependencies
```

---

## Prerequisites

1. **Java** (11 or above)
2. **Maven** (for dependency management)
3. **Allure CLI** (for generating test reports)
   - Install using:
     ```bash
     brew install allure
     ```
     or
     ```bash
     choco install allure
     ```

---

## Setup Instructions

1. **Clone the Repository**

   ```bash
   git clone <repository-url>
   cd selenium_task_
   ```

2. **Install Dependencies**

   ```bash
   mvn clean install
   ```

3. **Configure Test Environment**

   - Update `config.properties` for the desired browser and base URL:
     ```properties
     browser=chrome
     baseUrl=https://useinsider.com/
     ```

4. **Run Tests**

   - Using Maven:
     ```bash
     mvn clean test
     ```

5. **Generate Allure Reports**

   ```bash
   mvn allure:serve
   ```

---

## Key Features in Code

### Dynamic Screenshot Functionality

Screenshots are saved with unique names to prevent overwriting:

```java
public static void takeScreenshot(WebDriver driver, String testName) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    String timestamp = LocalDateTime.now().format(formatter);
    String fileName = testName + "_" + timestamp + ".png";
    // Save logic here
}
```

### TestNG Parallel Execution

Configured via `TestNG.xml`:

```xml
<suite name="Allure Test Suite" parallel="tests" thread-count="3">
    <listeners>
        <listener class-name="io.qameta.allure.testng.AllureTestNg" />
    </listeners>
    <test name="Homepage Tests">
        <classes>
            <class name="tests.HomePageTest" />
        </classes>
    </test>
</suite>
```

### Page Object Model

Each page class encapsulates UI element locators and actions. For example:

```java
public class HomePage {
    @Test(testName = "Verify Website")
    public void verifyHeaderText() {
        String actualHeader = homePage.getHeaderText("HomePage",expectedHeader);
        Assert.assertEquals(actualHeader, expectedHeader, "Header text doesn't match!");
    }
}
```

---

## Dependencies

Key dependencies defined in `pom.xml`:

```xml
<dependencies>
    <!-- Selenium -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.7.0</version>
    </dependency>

    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.8.0</version>
    </dependency>

    <!-- Allure -->
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-testng</artifactId>
        <version>2.21.0</version>
    </dependency>
</dependencies>
```
## **Contributors**

- **Irem Vardan**
    - API Testing Expert
    - [GitHub Profile](https://github.com/remvrdn)
---