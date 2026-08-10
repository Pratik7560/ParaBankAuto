package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import com.parabank.utils.Log;

public class BillPayPage extends BasePage {

    // Locators
    private By billPayLink = By.linkText("Bill Pay");
    private By payeeNameField = By.name("payee.name");
    private By addressField = By.name("payee.address.street");
    private By cityField = By.name("payee.address.city");
    private By stateField = By.name("payee.address.state");
    private By zipCodeField = By.name("payee.address.zipCode");
    private By phoneField = By.name("payee.phoneNumber");
    private By accountNumberField = By.name("payee.accountNumber");
    private By verifyAccountField = By.name("verifyAccount");
    private By amountField = By.name("amount");
    private By fromAccountIdDropdown = By.name("fromAccountId");
    private By sendPaymentButton = By.xpath("//input[@value='Send Payment']");
    private By successHeader = By.xpath("//h1[contains(text(),'Bill Payment Complete')]");

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    public void payBill(String name, String address, String city, String state, String zip, String phone, String accountNo, String amount) {
        // Step 1: Open Bill Pay Page
        click(billPayLink);

        // Step 2: Fill payee details
        type(payeeNameField, name);
        type(addressField, address);
        type(cityField, city);
        type(stateField, state);
        type(zipCodeField, zip);
        type(phoneField, phone);
        type(accountNumberField, accountNo);
        type(verifyAccountField, accountNo);
        type(amountField, amount);

        // Step 3: Wait 1 second and select account
        try { Thread.sleep(1000); } catch (Exception e) {}
        Select fromAccount = new Select(driver.findElement(fromAccountIdDropdown));
        fromAccount.selectByIndex(0);

        // Step 4: Click Send Payment
        click(sendPaymentButton);
        Log.info("Paid bill of $" + amount + " to payee: " + name);
    }

    public boolean isBillPayComplete() {
        // Wait 2 seconds for success screen to load
        try { Thread.sleep(2000); } catch (Exception e) {}
        try {
            return driver.findElement(successHeader).isDisplayed();
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
}