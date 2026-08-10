package com.parabank.tests.Ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.parabank.base.BaseTest;
import com.parabank.pages.RegisterPage;
import com.parabank.utils.Log;

public class RegisterTest extends BaseTest {

    @Test(description = "Verify new user registration functionality")
    public void testUserRegistration() {
        Log.startTestCase("testUserRegistration");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickRegisterLink();

        String dynamicUsername = "user_" + System.currentTimeMillis();
        registerPage.registerNewUser("Rahul", "Sharma", "FC Road", "Pune", "MH", "411001", 
                                    "999-00-1234", dynamicUsername, "Test@1234");

        String successMsg = registerPage.getRegistrationSuccessText();
        Assert.assertTrue(successMsg.contains("Your account was created successfully"), "Registration failed!");

        Log.endTestCase("testUserRegistration");
    }
}