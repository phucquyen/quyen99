package com.vti.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIContainer {
	@Bean
	public ModelMapper providerModelMapper() {
		return new ModelMapper();

	}
}
