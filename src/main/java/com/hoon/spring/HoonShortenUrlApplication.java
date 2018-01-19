package com.hoon.spring;

import com.hoon.spring.service.db.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class HoonShortenUrlApplication implements CommandLineRunner {

	@Autowired
	private TableService tableService;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(HoonShortenUrlApplication.class);
		//app.setAdditionalProfiles("production");
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*tableService.createTable();*/
		tableService.createSequence();
	}
}
