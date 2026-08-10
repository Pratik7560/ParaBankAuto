package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import com.parabank.utils.Log;

public class RequestLoanPage extends BasePage {

    // Locators
    private By requestLoanLink = By.linkText("Request Loan");
    private By loanAmountField = By.id("amount");
    private By downPaymentField = By.id("downPayment");
    private By fromAccountIdDropdown = By.id("fromAccountId");
    private By applyNowButton = By.xpath("//input[@value='Apply Now']");
    
    // Updated locator using ID: loanStatus
    private By loanStatusHeader = By.id("loanStatus");

    public RequestLoanPage(WebDriver driver) {
        super(driver);
    }

    public void applyForLoan(String amount, String downPayment) {
        // Step 1: Click Request Loan link
        click(requestLoanLink);

        // Step 2: Wait 2 seconds for dropdown options to load
        try { Thread.sleep(2000); } catch (Exception e) {}

        // Step 3: Enter loan amount and down payment
        type(loanAmountField, amount);
        type(downPaymentField, downPayment);

        // Step 4: Select first account from dropdown
        Select selectAccount = new Select(driver.findElement(fromAccountIdDropdown));
        selectAccount.selectByIndex(0);

        // Step 5: Click Apply Now button
        click(applyNowButton);

        // Step 6: Wait 2 seconds for result
        try { Thread.sleep(2000); } catch (Exception e) {}

        Log.info("Loan applied successfully.");
    }

    public boolean isLoanApproved() {
        try {
            return driver.findElement(loanStatusHeader).getText().equalsIgnoreCase("Approved");
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
}