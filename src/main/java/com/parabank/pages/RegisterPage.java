package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.parabank.utils.Log;

public class RegisterPage extends BasePage {

    // Locators
    private By registerLink = By.linkText("Register");
    private By firstNameField = By.id("customer.firstName");
    private By lastNameField = By.id("customer.lastName");
    private By addressField = By.id("customer.address.street");
    private By cityField = By.id("customer.address.city");
    private By stateField = By.id("customer.address.state");
    private By zipCodeField = By.id("customer.address.zipCode");
    private By ssnField = By.id("customer.ssn");
    private By usernameField = By.id("customer.username");
    private By passwordField = By.id("customer.password");
    private By confirmPasswordField = By.id("repeatedPassword");
    private By registerButton = By.xpath("//input[@value='Register']");
    private By successMessage = By.xpath("//p[contains(text(),'Your account was created successfully')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void clickRegisterLink() {
        click(registerLink);
    }

    public void registerNewUser(String firstName, String lastName, String address, String city, 
                               String state, String zipCode, String ssn, String username, String password) {
        Log.info("Registering new user with username: " + username);
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(addressField, address);
        type(cityField, city);
        type(stateField, state);
        type(zipCodeField, zipCode);
        type(ssnField, ssn);
        type(usernameField, username);
        type(passwordField, password);
        type(confirmPasswordField, password);
        click(registerButton);
    }

    public String getRegistrationSuccessText() {
        return getText(successMessage);
    }
}