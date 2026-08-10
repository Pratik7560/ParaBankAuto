package com.parabank.tests.Ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.parabank.base.BaseTest;
import com.parabank.pages.BillPayPage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.OpenAccountPage;
import com.parabank.pages.OverviewPage;
import com.parabank.pages.RequestLoanPage;
import com.parabank.pages.TransferPage;
import com.parabank.utils.ConfigReader;
import com.parabank.utils.Log;

public class EndToEndBankingTest extends BaseTest {

    @Test(description = "Verify Complete End-to-End Banking Journey")
    public void testCompleteBankingWorkflow() throws InterruptedException {
        Log.startTestCase("testCompleteBankingWorkflow");

        // 1. Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        OverviewPage overviewPage = new OverviewPage(driver);
        Assert.assertTrue(overviewPage.isAccountOverviewDisplayed(), "Login failed!");

        // 2. Open New Account
        OpenAccountPage openAccountPage = new OpenAccountPage(driver);
        openAccountPage.openAccount("SAVINGS");
        
        String newAccountId = openAccountPage.getNewAccountId();
        Log.info("Newly Generated Account ID: " + newAccountId);
        Assert.assertNotNull(newAccountId, "Failed to generate new account!");
        
        // 3. Transfer Funds
        TransferPage transferPage = new TransferPage(driver);
        transferPage.transferAmount("100", newAccountId);
        Assert.assertTrue(transferPage.isTransferComplete(), "Fund Transfer failed!");
        
        // 4. Pay Bill
        BillPayPage billPayPage = new BillPayPage(driver);
        billPayPage.payBill("John Doe", "123 Main St", "Pune", "MH", "411001", "9876543210", "100", "50");
        Assert.assertTrue(billPayPage.isBillPayComplete(), "Bill payment failed!");
        
        // 5. Request Loan
        RequestLoanPage loanPage = new RequestLoanPage(driver);
        loanPage.applyForLoan("1000", "100");
        Assert.assertTrue(loanPage.isLoanApproved(), "Loan failed!");

        Log.endTestCase("testCompleteBankingWorkflow");
    }
}