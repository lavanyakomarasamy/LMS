package com.newt.lms.config;

import java.text.SimpleDateFormat;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.newt.lms.mapper.ResourceMapper;
import com.newt.lms.model.jpa.dao.Employee;
import com.newt.lms.model.jpa.dto.EmployeeDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author lavanyak
 *
 */
@Configuration
@EnableTransactionManagement
public class AppConfig {
	
	@Bean
	@ConfigurationProperties(prefix = "lms.datasource")
	public DataSource orderServiceDS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		return builder;
	}
	
	@Bean
	MapperFacade mapperFacade() {
		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		factory.classMap(Employee.class, EmployeeDTO.class).byDefault().register();
		return factory.getMapperFacade();
	}
	
	@Bean
	public ResourceMapper resourceMapper() {
		return new ResourceMapper();
	}
  
}
