package restassured.framework.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
	
	
	@Bean
	ApplicationContextProvider ApplicationContextProvider() {
		return new ApplicationContextProvider();
	}

}
