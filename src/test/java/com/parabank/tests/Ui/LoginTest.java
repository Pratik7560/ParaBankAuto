package com.parabank.tests.Ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.parabank.base.BaseTest;
import com.parabank.pages.LoginPage;
import com.parabank.pages.OverviewPage;
import com.parabank.utils.ConfigReader;
import com.parabank.utils.Log;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify Valid Login")
    public void testValidLogin() {
        Log.startTestCase("testValidLogin");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        OverviewPage overviewPage = new OverviewPage(driver);
        Assert.assertTrue(overviewPage.isAccountOverviewDisplayed(), "Valid login failed!");

        Log.endTestCase("testValidLogin");
    }

    @Test(priority = 2, description = "Verify Invalid Login Error Message")
    public void testInvalidLogin() {
        Log.startTestCase("testInvalidLogin");

        LoginPage loginPage = new LoginPage(driver);
        
        // Pass wrong username and password
        loginPage.login("invalid_user_test", "wrong_pass_123");

        // Fetch the error message
        String actualErrorMessage = loginPage.getErrorMessage();
        Log.info("Captured Error Message: " + actualErrorMessage);

        // Verify that an error message was displayed
        Assert.assertNotEquals(actualErrorMessage, "Error Not Found", "Invalid login failed to display error message!");

        Log.endTestCase("testInvalidLogin");
    }
}