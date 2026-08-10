package com.parabank.tests.Ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.parabank.base.BaseTest;
import com.parabank.pages.BillPayPage;
import com.parabank.pages.LoginPage;
import com.parabank.utils.ConfigReader;
import com.parabank.utils.Log;

public class BillPayTest extends BaseTest {

    @Test(description = "Verify utility bill payment functionality")
    public void testBillPay() {
        Log.startTestCase("testBillPay");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        BillPayPage billPayPage = new BillPayPage(driver);
        billPayPage.payBill("John Doe", "123 Main St", "Pune", "MH", "411001", "9876543210", "12345", "50");
        Assert.assertTrue(billPayPage.isBillPayComplete(), "Bill payment failed!");

        Assert.assertTrue(billPayPage.isBillPayComplete(), "Bill payment failed!");

        Log.endTestCase("testBillPay");
    }
}