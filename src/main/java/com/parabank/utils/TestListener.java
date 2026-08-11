package com.parabank.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Object[] params = result.getParameters();

        // Append the first parameter (e.g., CustomerID) to test name for Data-Driven Testing
        if (params != null && params.length > 0 && params[0] != null) {
            testName += " [" + params[0].toString() + "]";
        }

        ExtentTest extentTest = extent.createTest(testName, result.getMethod().getDescription());
        test.set(extentTest);
        Log.info("TEST STARTED: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed Successfully: " + result.getMethod().getMethodName());
        Log.info("TEST PASSED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.get().log(Status.FAIL, result.getThrowable());
        Log.error("TEST FAILED: " + result.getMethod().getMethodName());

        // Capture screenshot for Selenium UI Tests and attach to Extent Report
        Object testClass = result.getInstance();
        WebDriver driver = null;

        try {
            // Retrieve public WebDriver instance from BaseTest
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(testClass);
        } catch (Exception e) {
            // API tests do not use WebDriver, so exception handling is expected here
            Log.info("No active WebDriver found for screenshot capture (likely an API test).");
        }

        if (driver != null) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName());
            test.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            Log.info("Attached screenshot to Extent Report for failed test.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        Log.warn("TEST SKIPPED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("Flushing Extent Reports to disk...");
        if (extent != null) {
            extent.flush();
        }
    }
}