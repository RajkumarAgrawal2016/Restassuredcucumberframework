package restassured.framework.support;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RedirectSpecification;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	/**
	 * 
	 * Create  a response object for a given GET Request Specification and URL 
	 * @param rspec
	 * @param url
	 */

	public static Response fetchGetResponse (RequestSpecification rspec, String url) {
	RequestSpecification httpRequest = RestAssured.given();
		
		return httpRequest.given().spec(rspec).get(url);	
	}
	
	/**
	 * 
	 * Create  a response object for a given POST Request Specification and URL 
	 * @param rspec
	 * @param url
	 * @param postJson
	 */

	public static Response fetchPostResponse (RequestSpecification rspec, String url, JSONObject postJson, String dateTime) {
	RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("X-DateTime", dateTime);
		return httpRequest.given().spec(rspec).body(postJson.toString()).when().post(url);	
	}
	/**
	 * 
	 * Create  a response object for a given POST Request Specification and URL 
	 * @param rspec
	 * @param url
	 * @param postJson
	 */

	public static Response fetchPostResponse (RequestSpecification rspec, String url, JSONObject postJson) {
	RequestSpecification httpRequest = RestAssured.given();
		return httpRequest.given().spec(rspec).body(postJson.toString()).when().post(url);	
	}
	
	/**
	 * 
	 * assert a given Response has a HTTP status of 200
	 * @param response
	 * 
	 */

	public static void responseStatusSuccessful (Response response) {
	        	response.then().statusCode(200);
	}
	
	/**
	 * 
	 * assert a given Response has a HTTP status of 400
	 * @param response
	 * 
	 */

	public static void responseStatusBadRequest (Response response) {
	        	response.then().statusCode(400);
	}
	
	
	/**
	 * 
	 * assert a given Response has a HTTP status of 500
	 * @param response
	 * 
	 */

	public static void responseStatusInternalServerError (Response response) {
	        	response.then().statusCode(500);
	}
	/**
	 * 
	 * assert a given Response has a HTTP status of 403
	 * @param response
	 * 
	 */

	public static void responseStatusUnauthorised (Response response) {
	        	response.then().statusCode(403);
	}
	/**
	 * 
	 * assert a given Response has a HTTP status of 404
	 * @param response
	 * 
	 */

	public static void responseStatusnotFound (Response response) {
	        	response.then().statusCode(404);
	}
	
	/**
	 * 
	 * assert a given Response has a HTTP status of 405
	 * @param response
	 * 
	 */

	public static void responseStatusnotallowed (Response response) {
	        	response.then().statusCode(405);
	}
	/**
	 * 
	 * assert a given Response has a HTTP status of 500
	 * @param response
	 * 
	 */

	public static void responseStatusFatal (Response response) {
	        	response.then().statusCode(500);
	}
	/**
	 * 
	 * assert each each item in a given List is  contained within a given String
	 * @param fields
	 * @param responsibility
	 * 
	 */

	public static void checkResponseBody(List<String> fields,String responseBody) {
	        	for (String item:fields) {
	        		Assert.assertTrue("'" +item+ "' not found in structure", responseBody.contains(item));
	        	}
	}
	/**
	 * 
	 * check all values of a given field match a given value
	 * reads from numerically indexed JSON
	 * @param field
	 * @param value
	 * @param response
	 */

	public static void checkStringValueInterative(String field,String value,Response response) {
		
		int responses = response.jsonPath().getInt("size()");
	        	for (int n= 0; n<responses; n++) {
	        		Map values = response.jsonPath().getMap(Integer.toString(n));
	        		String currentValue = values.get(field).toString(); 
	        		Assert.assertTrue("Status:'" + currentValue +"', is not '"+ value +"'",currentValue.equals(value));
	        	}
	}
	/**
	 * 
	 * check all values of a given field are not null
	 * reads from numerically indexed JSON
	 * @param field
	 * @param value
	 * @param response
	 */

	public static void checkStringNotNullIterative(String field,Response response) {
		
		int responses = response.jsonPath().getInt("size()");
	        	for (int n= 0; n<responses; n++) {
	        		Map values = response.jsonPath().getMap(Integer.toString(n));
	        		String currentValue = values.get(field).toString(); 
	        		Assert.assertTrue("Status: value found to be null", currentValue != null&& !currentValue.isEmpty());
	        	}
	}
}
