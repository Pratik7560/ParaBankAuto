package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.parabank.utils.Log;

public class LoginPage extends BasePage {

    // Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//input[@value='Log In']");
    private By errorMessage = By.xpath("//p[@class='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        Log.info("Logging in with username: " + username);
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    public String getErrorMessage() {
        try {
            Thread.sleep(1000); 
            return driver.findElement(errorMessage).getText();
        } catch (Exception e) {
            e.printStackTrace(); 
            return "Error Not Found";
        }
    }
}