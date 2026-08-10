package com.parabank.tests.Ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.parabank.base.BaseTest;
import com.parabank.pages.LoginPage;
import com.parabank.pages.OverviewPage;
import com.parabank.utils.ConfigReader;
import com.parabank.utils.Log;

public class OverviewTest extends BaseTest {

    @Test(description = "Verify Account Overview table details and total balance display")
    public void testAccountOverviewDetails() {
        Log.startTestCase("testAccountOverviewDetails");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        OverviewPage overviewPage = new OverviewPage(driver);
        overviewPage.navigateToAccountsOverview();

        Assert.assertTrue(overviewPage.isAccountOverviewDisplayed(), "Accounts Overview Header is missing!");
        
        String totalBalance = overviewPage.getTotalAccountBalance();
        Log.info("Total Balance retrieved: " + totalBalance);
        Assert.assertNotNull(totalBalance, "Total account balance is null!");

        Log.endTestCase("testAccountOverviewDetails");
    }
}