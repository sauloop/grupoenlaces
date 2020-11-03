package com.pablogiraldo.grupoenlaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GrupoenlacesApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GrupoenlacesApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GrupoenlacesApplication.class, args);
	}

}
