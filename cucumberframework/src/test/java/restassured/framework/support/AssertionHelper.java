package restassured.framework.support;

import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restassured.cucumberframework.db.DBQuery;
import restassured.cucumberframework.db.QueryHelper;

@Component
public class AssertionHelper {


	@Autowired
	public QueryHelper queryHelper;
	
	@Autowired
	private DBQuery doDBQuery;
	
	/**
	 * Assert that the genders in the response are as expected for the service
	 * @param serviceUid
	 * @param gender
	 * 
	 */
	
	public void assertGendersAreCorrect(String serviceUid, Map<String,Object> gender) {
		
		List<Map<String, Object>> expectedGender = doDBQuery.runQuery(queryHelper.servicegendersByServiceUid(serviceUid));
		assertListsAreEqual(expectedGender, gender,"List of genders");
		
	}
	
	public void assertServiceTypeIDAndNameAreCorrect(String serviceUid, Map<String,Object> serviceType) {
	
	Map<String,Object> expectedServiceType = doDBQuery.runQuery(queryHelper.serviceAncestryByServiceUid(serviceUid)).get(0);
	Assert.assertEquals("Unexpected service type(s)", expectedServiceType, serviceType);
	}
	public void assertTypeCorrect(String serviceUid, Map<String, Object> statusName) {
		
		Map<String,Object> expectedStatus = doDBQuery.runQuery(queryHelper.serviceAncestryByServiceUid(serviceUid)).get(0);
		Assert.assertEquals("Unexpected service type(s)", expectedStatus, statusName.get("name"));
		}
	
	public void assertDispositionsAreCorrect(String serviceUid, Map<String, Object> dispositions) {
		List<Map<String, Object>> expectedDispositionsRaw = doDBQuery.runQuery(queryHelper.servicegendersByServiceUid(serviceUid));
		List<Map<String, Object>> validDispositionsRaw = doDBQuery.runQuery(queryHelper.servicegendersByServiceUid(serviceUid));
		
		List<String> validDispositions = 
	}
	public void assertREferralAreCorrect(String serviceUid, List<String> referrals) {
		List<Map<String, Object>> expectedReferralRaw=  doDBQuery.runQuery(queryHelper.serviceReferralsByServiceUid(serviceUid));
		List<String> expectedReferrals = expectedReferralRaw.stream().map(m ->.get("name").toString())expectedReferralRaw.collect(Collectors.toString());
		assertOfStringAreEqual(expectedReferrals, referrals, "Referrals");
	}
	}

	
