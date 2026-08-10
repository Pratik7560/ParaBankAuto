package com.parabank.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.parabank.utils.ExcelUtils;
import com.parabank.utils.Log;

public class ApiBankingDataDrivenTest {

    /**
     * DataProvider reads records from Excel sheet named 'TestData'.
     * ExcelUtils gets the file path from ConfigReader using key 'excelPath'.
     */
    @DataProvider(name = "bankingExcelData")
    public Object[][] getBankingTestData() {
        Log.info("Initializing DataProvider: Fetching records from sheet 'TestData'...");
        
       
        Object[][] testData = ExcelUtils.getTestData("Sheet1");

        if (testData == null || testData.length == 0) {
            Log.error("No data found in Excel sheet 'TestData'!");
            throw new RuntimeException("Excel DataProvider returned empty or null array.");
        }

        Log.info("Successfully loaded " + testData.length + " test iterations for execution.");
        return testData;
    }

    @Test(
        dataProvider = "bankingExcelData", 
        description = "Data Driven Banking Transaction Validation using Records from Excel"
    )
    public void testCustomerTransactions(
            String customerId, 
            String accountNumber, 
            String amount, 
            String description, 
            String expectedStatus) {

        Log.startTestCase("testCustomerTransactions - Customer: " + customerId);

        Log.info("Processing Iteration Data -> CustomerID: " + customerId 
                + " | AccountNo: " + accountNumber 
                + " | Amount: $" + amount 
                + " | Description: " + description 
                + " | ExpectedStatusCode: " + expectedStatus);

        // 1. Mandatory Parameter Assertions
        Assert.assertNotNull(customerId, "CustomerID parameter should not be null!");
        Assert.assertFalse(customerId.trim().isEmpty(), "CustomerID should not be empty!");
        Assert.assertNotNull(accountNumber, "AccountNumber parameter should not be null!");

        // 2. Data Validation Logic
        double parsedAmount = Double.parseDouble(amount);
        int expectedStatusCode = Integer.parseInt(expectedStatus);

        if (parsedAmount <= 0) {
            Log.info("Executing Negative Amount Flow for amount: $" + parsedAmount);
            Assert.assertEquals(expectedStatusCode, 400, "Invalid/Negative amount must expect HTTP 400 status!");
        } else {
            Log.info("Executing Positive Amount Flow for amount: $" + parsedAmount);
            Assert.assertEquals(expectedStatusCode, 200, "Valid transaction amount must expect HTTP 200 status!");
        }

        Log.endTestCase("testCustomerTransactions - Customer: " + customerId);
    }
}