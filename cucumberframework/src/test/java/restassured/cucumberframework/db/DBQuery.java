package restassured.cucumberframework.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component

/**
 * 
 * @author Rajkumar Agrawal
 *Basic implementation to run bespoke queries
 *no need (yet) for full-blown JPA approach
 */

public class DBQuery {
	
	private static final Logger logger = LoggerFactory.getLogger(DBQuery.class);
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @param sql-the sql you want to run
	 * @return the result set for the query where each row is an entry in the list
	 * and each row is a map of name value pairs
	 * 
	 */
public List<Map<String,Object>> runQuery(String sql){ 

try {
	List<Map<String,Object>> results = jdbcTemplate.queryForList(sql);
	return results;
} catch (DataAccessException e) {
	
	logger.error(e.getMessage());
	return new ArrayList<Map<String,Object>>();
	
}

}

/**
 * 
 *@param sql-the sql you want to run 
 * 
 * 
 */
public void executeSql(String sql) {
	try {
		jdbcTemplate.execute(sql);
	}
	catch (DataAccessException e) {
	logger.error(e.getMessage(),e);
}
}

}
