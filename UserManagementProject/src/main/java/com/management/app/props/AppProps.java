package com.management.app.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableConfigurationProperties
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public class AppProps {
	
	private Map<String, String> messages = new HashMap<>();
	
	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}

	

	
	

}
