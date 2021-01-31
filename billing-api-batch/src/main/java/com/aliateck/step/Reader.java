package com.aliateck.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.aliateck.fact.domaine.ports.api.batch.BatchApiService;

public class Reader implements ItemReader<String>{
	
	
	


	@Override
	public String read() throws Exception {		
		//batchApiService.calculerFraisRetard();
		return null;
		
	}

}
