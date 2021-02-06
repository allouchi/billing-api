package com.aliateck.fact.application.rest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;




@Component
@PropertySource("classpath:application.properties")
public class StorageProperties {	
	

	@Value("${storage-pathRoot-pdf}")
	private String pathRoot;

	public String getPathRoot() {
		return pathRoot;
	}

	public void setPathRoot(String pathRoot) {
		this.pathRoot = pathRoot;
	}


}
