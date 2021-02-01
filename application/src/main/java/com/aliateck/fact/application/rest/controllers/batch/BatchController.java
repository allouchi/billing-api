package com.aliateck.fact.application.rest.controllers.batch;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.fact.application.rest.controllers.common.CommonResource.Resource;
import com.aliateck.fact.domaine.ports.api.batch.BatchApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping(Resource.BATCHS)
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BatchController {
	
	
	BatchApiService batchApiService;
	
	@PostMapping
	public void updateFraisRetard() {
		log.info("Batch calcul des frais de retard");
		//batchApiService.calculerFraisRetard();
	}

}
