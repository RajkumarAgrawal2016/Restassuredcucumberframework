package restassured.cucumberframework.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restassured.cucumberframework.hooks.Hooks;
import restassured.cucumberframework_java.SingleUser;
import restassured.framework.support.Utils;

public class UserNotFoundSteps {

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
@Given("^i requested of user for (.*)$")
	public void i_requested_of_user_for (String user) {
	singleuser.setUser(user);
}
@When("^i make a request for not found user$")
public void i_make_a_request_for_not_found_user() {
	
	
	route = singleuser.Route(singleuser.getUser());
	response = Utils.fetchGetResponse(rspec, route);
	
}
@Then("^not found details returned$")
public void not_found_details_returned() {
	Utils.responseStatusnotFound(response);
	bodyAsString = response.body().asString();
	System.out.println("Response body is:"+ bodyAsString);
	
}	
	
}
