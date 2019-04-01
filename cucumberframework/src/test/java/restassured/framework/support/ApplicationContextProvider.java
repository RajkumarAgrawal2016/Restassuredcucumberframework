package restassured.framework.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"uk.nhs.*"})
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext context;
	
	public static ApplicationContext getApplicationContext() {
		return context;
		
	}
	
	
	public void setApplicationContext (ApplicationContext ac) 
		throws BeansException{
			context = ac;
		}
		
	}

