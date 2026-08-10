package com.parabank.tests.Ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.parabank.base.BaseTest;
import com.parabank.pages.LoginPage;
import com.parabank.pages.TransferPage;
import com.parabank.utils.ConfigReader;
import com.parabank.utils.Log;

public class TransferTest extends BaseTest {

    @Test(description = "Verify fund transfer between accounts")
    public void testFundTransfer() throws InterruptedException {
        Log.startTestCase("testFundTransfer");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        TransferPage transferPage = new TransferPage(driver);
        transferPage.transferAmount("150", "12345");

        Assert.assertTrue(transferPage.isTransferComplete(), "Fund Transfer operation failed!");

        Log.endTestCase("testFundTransfer");
    }
}