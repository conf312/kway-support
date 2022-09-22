package com.kway.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KwaySupportApplication {

	public static void main(String[] args) {
		SpringApplication.run(KwaySupportApplication.class, args);
	}

}
