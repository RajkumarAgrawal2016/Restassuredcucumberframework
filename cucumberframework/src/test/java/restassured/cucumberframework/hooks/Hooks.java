package restassured.cucumberframework.hooks;

import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class Hooks {
	
	
	public static RequestSpecification beforeScenario(RequestSpecification rspec)
	
	{
	/**
	 * Set a default specification for all user interface end points tests
	 */
		
		RequestSpecBuilder build = new RequestSpecBuilder(); 
			
			//default host and expected payload
			
//			build.setBaseUri("https://api:8443");
//			build.setBasePath("/ui");
		    build.setBaseUri("https://reqres.in");
			build.setContentType(ContentType.JSON);
			build.addHeader("Content-Type", "application/json");
			
			//api authentication
			
			PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
			basicAuth.setUserName("pathwaysdos");
			basicAuth.setPassword("pathwaysdos");
			build.setAuth(basicAuth);
			
			//remove HTTPS validation or self certification will fail call
			
			build.setRelaxedHTTPSValidation();
			
			rspec =build.build();
			
			return rspec;
	}
}
