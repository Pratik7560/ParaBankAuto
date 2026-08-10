package com.parabank.api;

import io.restassured.response.Response;
import com.parabank.utils.Log;
import java.util.HashMap;
import java.util.Map;

public class BankApiController {

    // 1. User Authentication via Backend API
    public Response authenticateUser(String username, String password) {
        String endpoint = ApiEndpoints.LOGIN_API
                .replace("{username}", username)
                .replace("{password}", password);
        Log.info("Executing API: Authenticate User -> " + username);
        return RestClient.get(endpoint);
    }

    // 2. Fetch Accounts List via Backend API
    public Response getAccountsByCustomerId(String customerId) {
        String endpoint = ApiEndpoints.GET_CUSTOMER_ACCOUNTS
                .replace("{customerId}", customerId);
        Log.info("Executing API: Fetch Accounts for Customer -> " + customerId);
        return RestClient.get(endpoint);
    }

    // 3. Fund Transfer via Backend API (ParaBank expects Query Params on POST)
    public Response executeFundTransfer(String fromAccountId, String toAccountId, String amount) {
        Log.info("Executing API: Fund Transfer from " + fromAccountId + " to " + toAccountId + " | Amount: " + amount);
        
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("fromAccountId", fromAccountId);
        queryParams.put("toAccountId", toAccountId);
        queryParams.put("amount", amount);

        return RestClient.postWithQueryParamsMap(ApiEndpoints.TRANSFER_FUNDS_API, queryParams);
    }

    // 4. Deposit Money via Backend API
    public Response depositMoney(String accountId, String amount) {
        Log.info("Executing API: Deposit $" + amount + " to Account -> " + accountId);
        
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("accountId", accountId);
        queryParams.put("amount", amount);

        return RestClient.postWithQueryParamsMap(ApiEndpoints.DEPOSIT_FUNDS_API, queryParams);
    }
}