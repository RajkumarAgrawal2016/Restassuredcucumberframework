package restassured.cucumberframework.steps;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.List;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restassured.cucumberframework.hooks.Hooks;
import restassured.cucumberframework_java.SingleUser;
import restassured.framework.support.Utils;

public class SingleUsersStep {

	private RequestSpecification rspec;
	private Response response;
	private String route;
	private String bodyAsString;
	private SingleUser singleuser;

	@Before
	public void setup() {
		singleuser= new SingleUser();
		rspec = Hooks.beforeScenario(rspec);
	
	}
@Given("^i requested the single user for (.*)$")
	public void i_requested_the_single_user_for (String user) {
	singleuser.setUser(user);
}
@When("^i make a request for single user$")
public void i_make_a_request_for_single_user() {
	
	
	route = singleuser.Route(singleuser.getUser());
	response = Utils.fetchGetResponse(rspec, route);
	
}
@Then("^single user details returned$")
public void single_user_details_returned() {
	
	bodyAsString = response.body().asString();
	System.out.println("Response body is:"+ bodyAsString);
	
	
	
	
	
}	
}
