package com.aliateck.fact.batch.luncher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Scheduler {
	
	@Autowired
    private BatchLauncher batchLauncher;

    //@Scheduled(fixedDelay = 8000)
    public void perform() throws Exception {
        log.info("Batch programmé pour tourner toutes les 8 secondes");
        batchLauncher.run();
    }

}
