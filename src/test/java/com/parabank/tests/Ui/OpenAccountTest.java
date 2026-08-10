package com.parabank.tests.Ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.parabank.base.BaseTest;
import com.parabank.pages.LoginPage;
import com.parabank.pages.OpenAccountPage;
import com.parabank.utils.ConfigReader;
import com.parabank.utils.Log;

public class OpenAccountTest extends BaseTest {

    @Test(description = "Verify opening a new Savings account")
    public void testOpenNewSavingsAccount() {
        Log.startTestCase("testOpenNewSavingsAccount");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        OpenAccountPage openAccountPage = new OpenAccountPage(driver);
        openAccountPage.openAccount("SAVINGS");

        String newAccountId = openAccountPage.getNewAccountId();
        Log.info("New Account Generated ID: " + newAccountId);
        Assert.assertNotNull(newAccountId, "New Account creation failed!");

        Log.endTestCase("testOpenNewSavingsAccount");
    }
}