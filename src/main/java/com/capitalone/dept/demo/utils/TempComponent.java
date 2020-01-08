package com.capitalone.dept.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class TempComponent {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).disable(SerializationFeature.FAIL_ON_EMPTY_BEANS); 
	}
	
}
