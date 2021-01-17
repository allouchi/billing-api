package com.aliateck.fact.application.batch;

import com.aliateck.fact.domaine.ports.api.batch.BatchApiService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BatchStarter {
	
	 private BatchApiService batchApiService;
	 
	 

}
