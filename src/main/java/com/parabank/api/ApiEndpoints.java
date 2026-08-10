package com.parabank.api;

public class ApiEndpoints {
    // Correct ParaBank Direct REST Services Paths
    public static final String LOGIN_API = "/parabank/services/bank/login/{username}/{password}";
    public static final String TRANSFER_FUNDS_API = "/parabank/services/bank/transfer";
    public static final String GET_CUSTOMER_ACCOUNTS = "/parabank/services/bank/customers/{customerId}/accounts";
    public static final String DEPOSIT_FUNDS_API = "/parabank/services/bank/deposit";
}