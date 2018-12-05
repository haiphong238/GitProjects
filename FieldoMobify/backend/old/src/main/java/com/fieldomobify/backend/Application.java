package com.fieldomobify.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.fieldomobify.backend.events.CascadeSaveMongoEventListener;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = { "com.fieldomobify.backend" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CascadeSaveMongoEventListener userCascadingMongoEventListener() {
		return new CascadeSaveMongoEventListener();
	}

}
