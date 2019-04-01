package restassured.framework.support;

import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ChangeHelper {
	
	
	public static Map<String,Object> expectedCompleteChange(String guid, String serviceId){
		Map<String, Object> map= Stream.of(new String[][] {
			   {"uid", serviceId},
				{"approvestatus", "COMPLETE"},
				{"type","modify"},
				{"id",guid},
			
		}).collect(Collectors.toMap(data -> data[0],data -> data[1]));
		return map;
	}
	public static Map<String,Object> expectedPendingChange(String guid, String serviceId){
		Map<String, Object> map= Stream.of(new String[][] {
			   {"uid", serviceId},
				{"approvestatus", "PENDING"},
				{"type","modify"},
				{"id",guid},
			
		}).collect(Collectors.toMap(data -> data[0],data -> data[1]));
		return map;
	}
}
