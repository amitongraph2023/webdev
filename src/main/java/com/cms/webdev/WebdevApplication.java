package com.cms.webdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.cms.webdev"})
@SpringBootApplication
public class WebdevApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebdevApplication.class, args);
	}

}
