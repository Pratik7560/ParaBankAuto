package com.parabank.tests.API;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.parabank.api.BankApiController;
import com.parabank.utils.Log;

public class ApiBankingTest {

    private final BankApiController apiController = new BankApiController();
    private static String sessionId; // Store session ID or cookie if needed for chained calls

    @Test(priority = 1)
    public void testUserLoginApi() {
        Response response = apiController.authenticateUser("john", "demo");
        
        Log.info("Login Status Code: " + response.getStatusCode());
        Log.info("Login Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Login API Failed!");

        // Extract session ID or cookie if ParaBank returns one in headers/cookies
        // sessionId = response.getCookie("JSESSIONID"); 
    }

    @Test(priority = 2, dependsOnMethods = "testUserLoginApi")
    public void testFundTransferApi() {
        String fromAccount = "12345";
        String toAccount = "54321";
        String amount = "250";

        Response response = apiController.executeFundTransfer(fromAccount, toAccount, amount);
        
        Log.info("Transfer Status Code: " + response.getStatusCode());
        Log.info("Transfer Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Fund Transfer API Failed!");
        
        // Example: Validate body content to confirm business logic success
        Assert.assertTrue(
            response.getBody().asString().contains("Successfully transferred"), 
            "Response body did not confirm successful transfer!"
        );
    }
}