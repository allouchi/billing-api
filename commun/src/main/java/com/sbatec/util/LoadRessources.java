package com.sbatec.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class LoadRessources {
	
	private String custTemplate = "data/customTemplate.jrxml";
	private String custDefaultTemplate = "data/defaultTemplate.jrxml";
	private String suiviFacturation = "data/suivi-facturation.xls";
	
	public  Map<String, File> load() throws URISyntaxException {
		
		Map<String, File> map = new HashMap<>();
		URL custTemplateUrl = getClass().getClassLoader().getResource(custTemplate);
		URL custDefaultTemplateUrl = getClass().getClassLoader().getResource(custDefaultTemplate);
		URL suiviFacturationUrl = getClass().getClassLoader().getResource(suiviFacturation);
		File customFile = Paths.get(custTemplateUrl.toURI()).toFile();
		File defaultFile = Paths.get(custDefaultTemplateUrl.toURI()).toFile();
		File suiviFile = Paths.get(suiviFacturationUrl.toURI()).toFile();

		// File customFile = new ClassPathResource(custTemplate).getFile();
		// File defaultFile = new ClassPathResource(custDefaultTemplate).getFile();
		// File excelFile = new ClassPathResource(suiviFacturation).getFile();
		map.put("Default", defaultFile);
		map.put("Custom", customFile);
		map.put("Suivi", suiviFile);
		return map;
	}

}
