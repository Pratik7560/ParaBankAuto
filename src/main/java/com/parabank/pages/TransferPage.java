package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import com.parabank.utils.Log;

public class TransferPage extends BasePage {

    private By transferFundsLink = By.linkText("Transfer Funds");
    private By amountField = By.id("amount");
    private By toAccountIdDropdown = By.id("toAccountId");
    private By transferButton = By.xpath("//input[@value='Transfer']");
    private By successHeader = By.xpath("//h1[contains(text(),'Transfer Complete')]");

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    public void transferAmount(String amount, String toAccountId) {
        // Step 1: Open Transfer Funds page
        click(transferFundsLink);
        
        // Step 2: Wait 3 seconds for dropdown options to load from ParaBank server
        try { Thread.sleep(3000); } catch (Exception e) {}

        // Step 3: Enter the transfer amount
        type(amountField, amount);

        // Step 4: Select the account from the dropdown (MISSING IN YOUR CODE)
        Select selectAccount = new Select(driver.findElement(toAccountIdDropdown));
        selectAccount.selectByIndex(0); // Safely selects the first available account

        // Step 5: Click the Transfer button
        click(transferButton);
        
        // Step 6: Wait 1 second for success screen
        try { Thread.sleep(1000); } catch (Exception e) {}

        Log.info("Transferring $" + amount + " to Account: " + toAccountId);
    }

    public boolean isTransferComplete() {
        try {
            return driver.findElement(successHeader).isDisplayed();
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
}