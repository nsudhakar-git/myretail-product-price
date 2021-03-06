package com.myretail;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			if (logger.isDebugEnabled()) {
				logger.info("Inspect the beans provided by Spring Boot:");

				String[] beanNames = ctx.getBeanDefinitionNames();
				Arrays.sort(beanNames);

				for (String beanName : beanNames) {
					logger.debug(beanName);
				}
			}

		};
	}

}
