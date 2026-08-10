package com.parabank.api;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.parabank.utils.ConfigReader;
import com.parabank.utils.Log;
import java.util.Map;

public class RestClient {

    // Base Request Configurer (Industrial Standard Base Spec)
	private static RequestSpecification getBaseSpec() {
	    String baseUrl = ConfigReader.getProperty("base.url");
	    return given()
	            .baseUri(baseUrl)
	            .relaxedHTTPSValidation(); // JSON strictness काढली
	}

       
    // Generic GET Request Handler
    public static Response get(String endpoint) {
        Log.info("API GET Request: " + endpoint);
        return getBaseSpec()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // Generic POST Request with Map of Query Parameters
    public static Response postWithQueryParamsMap(String endpoint, Map<String, String> queryParams) {
        Log.info("API POST Request to: " + endpoint + " with params: " + queryParams);
        return getBaseSpec()
                .queryParams(queryParams)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    // Generic POST Request with Specific Parameters
    public static Response postWithQueryParams(String endpoint, String fromAccountId, String toAccountId, String amount) {
        Log.info("API POST Request: " + endpoint + " | Amount: " + amount);
        return getBaseSpec()
                .queryParam("fromAccountId", fromAccountId)
                .queryParam("toAccountId", toAccountId)
                .queryParam("amount", amount)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }
}