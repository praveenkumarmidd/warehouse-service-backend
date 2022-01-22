package com.example.warehouseservice.config;

import java.text.SimpleDateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WarehouseServiceConfig
{
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder()
	{
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);

		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

		builder.configure(mapper);
		return builder;
	}
}
