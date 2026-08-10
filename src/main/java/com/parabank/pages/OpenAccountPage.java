package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import com.parabank.utils.Log;

public class OpenAccountPage extends BasePage {

    // Locators
    private By openNewAccountLink = By.linkText("Open New Account");
    private By accountTypeDropdown = By.id("type");
    private By openAccountButton = By.xpath("//input[@value='Open New Account']");
    private By newAccountId = By.id("newAccountId");

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToOpenAccountPage() {
        click(openNewAccountLink);
    }

    // EndToEndBankingTest मधील openAccount() ला मॅच होण्यासाठी
    public void openAccount(String accountType) {
        navigateToOpenAccountPage();
        Log.info("Selecting Account Type: " + accountType);
        Select select = new Select(driver.findElement(accountTypeDropdown));
        select.selectByVisibleText(accountType);
        
        try { Thread.sleep(1000); 
        } catch (Exception e) {
        	e.printStackTrace();
        	}
        click(openAccountButton);
    }

    public String getNewAccountId() {
        return getText(newAccountId);
    }
}