package com.parabank.tests.Ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.parabank.base.BaseTest;
import com.parabank.pages.LoginPage;
import com.parabank.pages.RequestLoanPage;
import com.parabank.utils.ConfigReader;
import com.parabank.utils.Log;

public class RequestLoanTest extends BaseTest {

    @Test(description = "Verify customer loan application process")
    public void testLoanRequest() {
        Log.startTestCase("testLoanRequest");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        RequestLoanPage loanPage = new RequestLoanPage(driver);
        loanPage.applyForLoan("--", "100");
        Assert.assertTrue(loanPage.isLoanApproved(), "Loan failed!");

        Log.endTestCase("testLoanRequest");
    }
}