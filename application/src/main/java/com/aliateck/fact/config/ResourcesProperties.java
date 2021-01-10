package com.aliateck.fact.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:resources.properties")
public class ResourcesProperties {

	@Value("${path-file-facture}")
	private String pathFile;

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	@Override
	public String toString() {
		return "ResourcesProperties [status=" + pathFile + "]";
	}

}
