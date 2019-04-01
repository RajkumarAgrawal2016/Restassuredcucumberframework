package restassured.cucumberframework_java;

public class SingleUser {

	private String user;
	
	
	private String route = "/api/users/";
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String Route(String user) {
		return route + user;
	}
	
	
}
