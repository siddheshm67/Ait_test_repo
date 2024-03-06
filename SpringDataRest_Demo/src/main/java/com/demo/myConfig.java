package com.demo;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

//to block unwanted requests
@Configuration
public class myConfig implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		HttpMethod[] methods = {HttpMethod.PUT,HttpMethod.DELETE};
		config.getExposureConfiguration()
				.forDomainType(BookEntity.class)
				.withItemExposure((metadata,http)-> http.disable(methods))
				.withCollectionExposure((metadata,http)-> http.disable(methods));
	}

	
}
