package restassured.cucumberframework.db;

import org.springframework.stereotype.Component;


@Component
public class QueryHelper {
	
	//Large,bespoke queries
	
	public String serviceDetalsByServiceUid(String serviceUid) {
		return "select * from pathwaydos.services s\n" +
	"where s.uid = '" + serviceUid + "';";
	}
 public String serviceClinicalQueryByUid(String serviceUid) {
	 return "select s.id,\n" +
             "\t\ts.uid,\n" +
			 "t\ts.name,\n"+
             "t\ts.typeid\n"+
			 "from pathwaysdos.services s\n"+
             "where s.uid = '" +serviceUid+ "';";
 }
 
 public String systomGroupByServiceUid(String serviceUid) {
	 return "select sg.id,\n"+
            "\t\tsg.\"name\" as description,\n" +
			 "\t\tcount(sgds.sdid) as count,\n"+
            "\t\tsg.id as sg\n" +
			 "from pathwaysdos.servicesgsds sgds\n" +
            "join pathwaysdos.symptomgroup sg \n" +
			 "on sg.id = sgds.sgid\n" +
            "join pathwaysdos.services s \n" +
			 "on s.id = sgds.serviceid\n" +
            "where s.uid = '" +serviceUid + "'\n" +
			 "group by sg.id,sgds.sgid\n" +
            "order by sgds.sgid asc;";
 }
 
 
 public String symptomDiscriminatorsByServiceUidAndGroupIds(String serviceId, String groupId) {
	return "select distinict(sd.id) as id,\n"+
           "\t\tsd.description,\n" +
			"t\tsg.sgid as sgid\n" +
           "from pathwaysdos.symptomdiscriminators sd\n"+
			"join pathwaysdos.servicegsds sg\n" +
           "on sg.sdid = sd.id\n"+
			"join pathwaysdos.services s \n"+
           "on s.id = sg.serviceid\n" +
			"where sg.sgid in (" + groupId + ")\n"+
           "and s.uid = '" +serviceId + "';";
          		 
 }
 
 // queries for sub-elements of responses
 
 public String servicegendersByServiceUid(String serviceUid) {
	 return "select g.letter,g.\"name\" from pathwaysdos.genders g\n" +
             "join pathwaysdos.servicegenders sg \n" +
			 "on sg.genderid = g.id\n" +
             "join pathwaysdos.services s \n" +
              "on s.id = ar.serviceid\n" +
             "where s.uid = '" +serviceUid + "';";
 }
 
 public String serviceAncestryByServiceUid(String serviceUid) {
	 
	 return "select parent.uid as parent_uid, parent.\"name\" as parent_name, gp.uid as gp_uid, gp.\"name\" as gp_name "+
	      "from pathwaysdos.services s "+
			 "inner  join pathwaysdos.services parent on s.parentid = parent.id "+
	      "inner join pathwaysdos.services gp on parent.parentid = gp.id "+
			 "where s.uid = '" +serviceUid + "';";
	 
 }
 public String openingDaysAndTimesByServices(String serviceUid) {
	 
	 return "select ssod.\"date\", ssot.isclosed, ssot.starttime, ssot.endtime from pathways.services s\n" +
	 		 "join pathwaysdos.servicesspecifiedopeningdates ssod \n"+
			 "on ssod.serviceid = s.id\n"+
	 		 "join pathwaysdos.servicespecifiedopeningtimes ssot\n" +
			 "on ssot.servicespecifiedopeningdateid = ssod.od\n" +
	 		 "where s.uid = '" + serviceUid + "';";
	 
 }
 
 public String openingDaysAndTimeByServiceUid(String serviceUid) {
	 return "select otd.\"name\", sdot.starttime,sdot.endtime\n" +
            "from pathwaysdos.services s\n" +
			 "join ppathwaysdos.servicedayopenings sdo \n" +
            "on sdo.serviceid = s.id\n" +
			 "join pathwaysdos.openingtimedays oth \n" +
            "on otd.id = sdo.dayid\n"+
			 "join pathwaysdos.servicedayopeningtimes sdot \n" +
            "on sdot.servicedayopeningid = sdo.id\n" +
			"where s.id = '" +serviceUid + "';";
	 
 }
 public String serviceReferralInstructionsByUid(String serviceUid) {
	 return "SELECT uid, name, publicReferralInstructions "+
           "FROM pathwaysdos.services"+
			 "where uid = '" +serviceUid + "'";
 }
 public String serviceValidatePostcodebyPostcode(String postcode) {
	 return "SELECT postcode,easting, norting,postaltown, postAddress " +
			 "FROM pathwaysdos.locations " +
			 "WHERE postcode = '" + postcode + "';";
 }
}
