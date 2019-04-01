package restassured.cucumber.boot;

import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@PropertySource("classpath:application.properties")

public class BootApplication {
	
private static final Logger logger = LoggerFactory.getLogger(BootApplication.class);

public class DBConfig{
	
	@Autowired
	
	private Environment env;
	
	@Bean
	public DataSource getDataSource() {
		String url = env.getProperty("database.url");
		String username = env.getProperty("database.username");
		String dbPassword = env.getProperty("database.password");
		String driverClassname = env.getProperty("database.driverClassname");
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassname);
		
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(dbPassword);
		
		return dataSource;
	}
	
	@Bean 
	public NamedParameterJdbcTemplate nameParameterJdbcTemplate() {
		NamedParameterJdbcTemplate nameParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.getDataSource());
		return nameParameterJdbcTemplate;
		
	}
	@Bean
	public JdbcTemplate jdbcTemplate() {
		
		try {
			return new JdbcTemplate(getDataSource());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
		
	}
}

}
