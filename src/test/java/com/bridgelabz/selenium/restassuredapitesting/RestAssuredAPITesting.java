package com.bridgelabz.selenium.restassuredapitesting;

import org.json.JSONException;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Vijaykumar Bhavanur purpose: To demonstrate Restassured API Testing
 * 
 */
public class RestAssuredAPITesting {

	/**
	 * Method to test REST API End point with HTTP GET method using Restassured
	 */
	@Test
	public void testGetUsers() {
		// Base API URL
		RestAssured.baseURI = "https://reqres.in";

		// Building request to send for REST API End point
		RequestSpecification httpRequest = RestAssured.given();

		// Hitting REST API End point
		Response response = httpRequest.request(Method.GET, "/api/users?page=2");

		// Fetching status code from response
		int statusCode = response.getStatusCode();

		// comparing whether status code is 200
		Assert.assertEquals(200, statusCode);

		// Fetching response body as simple string
		String responseBody = response.getBody().asString();

		// Printing response
		System.out.println("Response Body in get method call =>  " + responseBody);

		// Printing value of specific field
		System.out.println("response:::" + response.jsonPath().getString("data[0]"));

	}

	/**
	 * Method to test REST API End point with HTTP POST method using Restassured
	 */
	@Test
	public void testRegister() throws JSONException {

		// Base API URL
		RestAssured.baseURI = "https://reqres.in";

		// Building JSON Payload to send for REST Endpoint
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "eve.holt@reqres.in");
		requestParams.put("password", "pistol");

		// Building request to send for REST API End point with header and payload
		RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json")
				.body(requestParams.toString());

		// Hitting REST API End point
		Response response = httpRequest.request(Method.POST, "api/register");

		// Fetching response body as simple string
		String responseBody = response.getBody().asString();
		System.out.println("Response Body in post call =>  " + responseBody);

		// comparing whether status code is 200
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);

	}
}
