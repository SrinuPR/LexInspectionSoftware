package com.deloitte.inspection.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class})
@ComponentScan(basePackages={"com.deloitte.inspection"})
public class LexInspectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LexInspectionApplication.class, args);
	}
}
