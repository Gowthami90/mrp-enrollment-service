package com.citi.membership.enrollment.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.citi.membership.enrollment")
public class EnrollmentConfiguration extends WebMvcConfigurerAdapter {
	
	@Value("${db_url}")
	String dbUrl;
	@Value("${username}")
	String username;
	@Value("${password}")
	String password;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		super.addCorsMappings(registry);
		registry.addMapping("/**").allowedHeaders("*").allowedMethods("GET", "POST","PUT", "DELETE").allowedOrigins("*");
	}
	@Bean
	@Profile("dev")
	public PropertySourcesPlaceholderConfigurer devEnvProperties() {
		System.out.println("load dev environment properties file");
		PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
		placeHolder.setLocation(new ClassPathResource("properties/service/card-details-service-dev.properties"));
		return placeHolder;
	}
	
	@Bean
	@Profile("test")
	public PropertySourcesPlaceholderConfigurer qaEnvProperties() {
		System.out.println("load test environment properties file");
		PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
		placeHolder.setLocation(new ClassPathResource("properties/service/card-details-service-test.properties"));
		return placeHolder;
	}
	
	@Bean
	@Profile("uat")
	public PropertySourcesPlaceholderConfigurer uatEnvProperties() {
		System.out.println("load uat environment properties file");
		PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
		placeHolder.setLocation(new ClassPathResource("properties/service/card-details-service-uat.properties"));
		return placeHolder;
	}
	
	@Bean
	@Profile("prod")
	public PropertySourcesPlaceholderConfigurer prodEnvProperties() {
		System.out.println("load prod environment properties file");
		PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
		placeHolder.setLocation(new ClassPathResource("properties/service/card-details-service-prod.properties"));
		return placeHolder;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	public JdbcTemplate jdbcTemplate() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		return new JdbcTemplate(dataSource);
		
		
	}
	
}


