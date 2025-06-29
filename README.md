# dailyfinance-api-automation

This project automates the testing of the [DailyFinance](https://dailyfinance.roadtocareer.net/) web application APIs using:

-  **Rest Assured** for HTTP request automation
-  **TestNG** as the testing framework
-  **Gradle** for build management
-  **Allure** for rich and interactive test reporting
-  **POM (Page Object Model)** architecture for maintainable test code

It covers essential user and item-related endpoints including registration, login, item management, and admin operations.

##  Project Structure

```
dailyfinance-api-automation/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── .gitignore
├── README.md
├── images/                        # Contains Allure report screenshots
├── src/
│   └── test/
│       ├── java/
│       │   ├── config/
│       │   │   ├── ItemModel.java     # POJO for item API payload
│       │   │   ├── Setup.java         # Loads config.properties
│       │   │   └── UserModel.java     # POJO for user API payload
│       │   ├── controller/
│       │   │   └── UserController.java # All API actions (register, login, etc.)
│       │   ├── testrunner/
│       │   │   └── UserTestRunner.java # All test cases with TestNG
│       │   └── utils/
│       │       └── Utils.java          # Random number generator, setEnvVar
│       └── resources/
│           ├── config.properties       # Stores tokens, IDs, and credentials
│           └── suite.xml              # TestNG suite file

```


##  Tech Stack

| Tool / Framework | Purpose |
|------------------|---------|
| **Java 11+**     | Programming language |
| **Gradle**       | Build tool and dependency management |
| **Rest Assured** | HTTP client for API automation |
| **TestNG**       | Test runner and assertions |
| **Allure**       | Test reporting and visualization |
| **Faker**        | Generating random fake user data |
| **Jackson**      | Object-to-JSON serialization |
| **Lombok**       | (Optional) Boilerplate code reduction |
