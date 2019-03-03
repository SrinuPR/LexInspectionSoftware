package com.deloitte.inspection.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = {MongoRepositoriesAutoConfiguration.class})
@PropertySource("classpath:Application.properties") 
@ComponentScan(basePackages={"com.deloitte.inspection"})
public class LexInspectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LexInspectionApplication.class, args);
	}
}
