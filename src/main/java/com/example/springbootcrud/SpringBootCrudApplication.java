package com.example.springbootcrud;

import com.example.springbootcrud.controller.PersonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootCrudApplication extends SpringBootServletInitializer {
	private static final Logger logger = LogManager.getLogger(SpringBootCrudApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudApplication.class, args);
		logger.debug("Debugging log");
		logger.info("Info log");
		logger.warn("Hey, This is a warning!");
		logger.error("Oops! We have an Error. OK");
		logger.fatal("Damn! Fatal error. Please fix me.");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootCrudApplication.class);
	}
}
