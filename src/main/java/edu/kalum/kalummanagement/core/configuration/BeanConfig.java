package edu.kalum.kalummanagement.core.configuration;

import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;

public class BeanConfig {
	@Bean
	public Gson Gson() {
		return new Gson();
	}
}
