package com.aliateck.fact.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:httpStatus.properties")
public class HttpStatusProperties {

	@Value("${user-status}")
    private String status;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "HttpStatusProperties [status=" + status + "]";
	}
	
	

   
}
