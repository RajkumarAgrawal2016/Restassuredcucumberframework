package restassured.cucumberframework.steps;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.containsString;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import restassured.cucumberframework.db.DBQuery;
import restassured.cucumberframework.db.QueryHelper;
import restassured.cucumberframework.hooks.Hooks;
import restassured.framework.support.Utils;

public class ListUsersSteps {
	
	private RequestSpecification rspec;
	private Response response;
	private String bodyAsString;

	
	@Autowired
	private DBQuery dbquery;
	
	@Autowired
	private QueryHelper queryhelper;
	
	@Before
	public void setup() {
		
		rspec = Hooks.beforeScenario(rspec);
		
	}
@Given("^i requested the user$")
	public void i_requested_the_user_for () {

}
@When("^i make a request$")
public void i_make_a_request() {
	
	response = Utils.fetchGetResponse(rspec, "/api/unknown");
	
}
@Then("^list users returned$")
public void list_users_returned() {
	
	bodyAsString = response.body().asString();
	System.out.println("Response body is:"+ bodyAsString);
	
}
@Then("^valid structure of the data return$")
	public void valid_structure_of_the_data_return(List<String> fields) {
	
	Utils.responseStatusSuccessful(response);
	
//	if(message!= null) {
//		response.then().body(message);
//	}
	
	Utils.checkResponseBody(fields, bodyAsString);
		
	}
@Then("^all the fields expected for the resqres are listed$")
public void all_the_fields_expected_for_the_resqres_are_listed() {

	response.then().statusCode(200).body(matchesJsonSchemaInClasspath("schemas/json/ReqRes.json"));

}

@Then("^list of user verified$")
public void list_of_user_verified() {
   
//	Map<String, String> responsebody = response.jsonPath().get();
//	String sql= queryHelper.returnChangeRecord(responsebody.get(0));
//	List <Map<String,Object>> results = queryChanges(sql);
//	String changeStatus = (String) results.get(0).get("approvestatus");
//	assertTrue(changeStatus.equalsIgnoreCase(approvestatus));
	
//	Map<String ,Object> expectedServiceDemographics = dbquery.runQuery(queryHelper.serviceDemographicsQueryByUid(serviceUid)).get(0);
//	Map<String,Object> actualServiceDemographics = response.jsonPath().get();
//	
//	String expectedStatusname = dbquery.runQuery(queryHelper.statusIdAndNameByServiceUID(serviceUid)).get(0).get("name").toString();

	response.then().statusCode(200).body(containsString("Retired Service"));
	
	@Then("^the service demographics are returned correctly$")
	public void theservicedemographicsarereturnedcorrectly() {
		
		Map<String,Object> expectedServiceDemographics = dbquery.runQuery(queryhelper.serviceDemographicsQueryByUid(serviceUid)).get(0);
		Map<String, Object> actualServiceDemographics = response.jsonPath().get();
		
		String expectedstatusName= dbquery.runQuery(queryhelper.statusIdAndNameByServiceUid(serviceUid)).get(0).get("name").toString();
		
		//Assertion on primitive data
		Assert.assertEquals("Id for service is incorrect", expectedServiceDemographics.get("id"), response.jsonPath().get("id"));
		assertionHelper.assertAncestryIsCorrect(serviceUid, response.jsonPath().get("address"));
		
	}
	
	
}
public String returnChangeRecord(String changeId) {
	return "select * from pathwaysdos.changes c where c.id = '" + changeId + "'";
}

private List<Map<String, Object>> queryChanges(String sql){
	return dbquery.runQuery(sql);
}
}
