package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.parabank.utils.Log;

public class OverviewPage extends BasePage {

    // Locators
    private By accountsOverviewLink = By.linkText("Accounts Overview");
    private By accountTableTitle = By.xpath("//h1[contains(text(),'Accounts Overview')]");
    private By totalBalanceAmount = By.xpath("//b[contains(text(),'Total')]/parent::td/following-sibling::td");
    private By logOutLink = By.linkText("Log Out");

    public OverviewPage(WebDriver driver) {
        super(driver);
    }
    public boolean isAccountOverviewDisplayed() {
        try {
            return driver.findElement(accountTableTitle).isDisplayed();
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
    public void navigateToAccountsOverview() {
        click(accountsOverviewLink);
    }

    public String getPageHeaderTitle() {
        return getText(accountTableTitle);
    }

    public String getTotalAccountBalance() {
        Log.info("Fetching total accounts balance from Overview table.");
        return getText(totalBalanceAmount);
    }

    public void clickLogout() {
        Log.info("Logging out from application.");
        click(logOutLink);
    }
}