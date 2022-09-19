package com.kway.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@EnableJpaAuditing
@SpringBootApplication
public class KwaySupportApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(KwaySupportApplication.class, args);
	}

}
