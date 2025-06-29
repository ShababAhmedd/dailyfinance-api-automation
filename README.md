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
├── images/                   # Allure report screenshots
├── src/
   └── test/
       ├── java/
       │   ├── config/       # Setup, Models (UserModel, ItemModel)
       │   ├── controller/   # API Controller methods
       │   ├── testrunner/   # TestNG Runner (UserTestRunner)
       │   └── utils/        # Env var and random data utilities
       └── resources/
           └── suite.xml     # TestNG Suite configuration
```
