package restassured.cucumberframework;

import org.junit.Test;

import static io.restassured.RestAssured.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
   
{
	@Test
	public void testStatusCode() {
		given().get("https://reqres.in/api/users?page=2").then().statusCode(200);
	}
	
	
}
