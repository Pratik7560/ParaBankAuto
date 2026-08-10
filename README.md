# 🏦 ParaBank End-to-End Hybrid Automation Framework

A comprehensive, production-grade **Hybrid Test Automation Framework** built with **Java, Selenium WebDriver, RestAssured, TestNG, Apache POI, and Extent Reports** to execute end-to-end UI and API tests on the ParaBank Web Application.

---

## 🚀 Key Framework Features

* **Page Object Model (POM):** Complete UI abstraction covering core banking modules (Transfer, Bill Pay, Open Account, Request Loan, etc.).
* **API Automation (REST Assured):** Dedicated API client architecture (`RestClient`, `ApiEndpoints`, `BankApiController`) for automated REST API testing.
* **Hybrid & Data-Driven Testing:** Integrated with **Apache POI** for reading Excel data driven test suites using TestNG `@DataProvider`.
* **Execution Reporting & Screenshots:** Integrated `TestListener` with **Extent Reports** to dynamically capture timestamped HTML execution reports and attach failure screenshots.
* **Centralized Utilities & Logging:** Configured with `Log4j2` for detailed logging and `ConfigReader` for dynamic environment properties.

---

## 📁 Project Directory Structure

```text
ParaBankAuto
├── reports                               # Timestamped Extent Reports
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── parabank
│   │               ├── api               # REST API Layer
│   │               │   ├── ApiEndpoints.java
│   │               │   ├── BankApiController.java
│   │               │   └── RestClient.java
│   │               ├── pages             # UI Page Objects (POM)
│   │               │   ├── BasePage.java
│   │               │   ├── BillPayPage.java
│   │               │   ├── LoginPage.java
│   │               │   ├── OpenAccountPage.java
│   │               │   ├── OverviewPage.java
│   │               │   ├── RegisterPage.java
│   │               │   ├── RequestLoanPage.java
│   │               │   └── TransferPage.java
│   │               └── utils             # Utility Helpers
│   │                   ├── ConfigReader.java
│   │                   ├── ExcelUtils.java
│   │                   ├── ExtentReportManager.java
│   │                   ├── Log.java
│   │                   ├── ScreenshotUtils.java
│   │                   └── TestListener.java
│   └── test
│       ├── java
│       │   └── com
│       │       └── parabank
│       │           ├── base              # Test Base & Driver Setup
│       │           │   └── BaseTest.java
│       │           └── tests             # Test Suites
│       │               ├── API           # API Test Classes
│       │               │   └── ApiBankingTest.java
│       │               └── Ui            # UI Automation Suites
│       │                   ├── ApiBankingDataDrivenTest.java
│       │                   ├── BillPayTest.java
│       │                   ├── EndToEndBankingTest.java
│       │                   ├── LoginTest.java
│       │                   ├── OpenAccountTest.java
│       │                   ├── OverviewTest.java
│       │                   ├── RegisterTest.java
│       │                   ├── RequestLoanTest.java
│       │                   └── TransferTest.java
│       └── resources
│           ├── config                    # Properties Configuration
│           │   └── config.properties
│           ├── testdata                  # Excel Test Data Sheets
│           │   └── TestData.xlsx
│           └── log4j2.xml                # Logging Configuration
├── .gitignore                            # Untracked Files Configuration
├── pom.xml                               # Project Build Dependencies
└── testng.xml                            # TestNG Execution Suite Configuration
