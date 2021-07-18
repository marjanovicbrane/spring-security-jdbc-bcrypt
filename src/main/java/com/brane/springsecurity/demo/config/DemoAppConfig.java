package com.brane.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;


//THIS IS JAVA CONFIG CLASS.WE ARE USING PURE JAVA CONFIG, NO XML
@Configuration
//with this annotation we enable Spring MVC
@EnableWebMvc
//base package where to scan components for Controller class,Service class,Reporistory class...
@ComponentScan(basePackages="com.brane.springsecurity.demo")
//Classpath is a parameter in the Java Virtual Machine or the Java compiler 
//that specifies the location of user-defined classes and packages. 
//The parameter may be set either on the command-line, or through an environment variable.
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
	
	//this variable env holds properties from the persistence-mysql.properties file
	//THIS IS GLOBAL ENVIRONMENT VARIABLE
	//Annotation @PropertySource READS DATA FROM THE PROPERTIES FILE AND 
	//WE ARE GOING TO INJECT THAT DATA INTO VARIABLE env
	//ENVIRONMENT INTERFACE REPRESENTS THE ENVIRONMENT IN WHICH THE CURRENT APPLICATION IS LAUNCHED 
	//we are going to inject Environment object so we can get properties from the file persistence-mysql.properties file
	@Autowired
	private Environment env;
	
	//we are going to set logger just for diagnostics
	private Logger logger=Logger.getLogger(getClass().getName());
	

	
	//We are creating here spring bean for View Resolver.
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		
		//prefix
		viewResolver.setPrefix("/WEB-INF/view/");
		
		//suffix
		viewResolver.setSuffix(".jsp");
		
		//return that object
		return viewResolver;
	}
	
	
	//we created a method which returns DataSource object
	//in this method we set all our props for our database
	@Bean
	public DataSource securityDataSource() {
		
		// create connection pool, this class is from c3p0 package
		ComboPooledDataSource securityDataSource=new ComboPooledDataSource();
		
		// set the jdbc driver
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} 
		catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		//let's log url and user ... just to make sure we are reading the data
		logger.info(">>> jdbc.url="+env.getProperty("jdbc.url"));
		logger.info(">>> jdbc.user="+env.getProperty("jdbc.user"));
		
		// set database connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool props
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	// need a helper method, read environment property and convert to int
	private int getIntProperty(String propName) {
		
		String propVal=env.getProperty(propName);
		
		// now convert to int
		int intPropVal=Integer.parseInt(propVal);
		
		return intPropVal;
	}
	
}
