package com.aliateck.fact.batch.luncher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Scheduler {

	@Autowired
	private BatchLauncher batchLauncher;

	// Déclenchement à 00H chaque jour
	// @Scheduled(cron = "0 0/01 * * * ?")
	public void perform() throws Exception {
		log.info("Batch programmé pour tourner tous les jours à 00H00");
		// batchLauncher.run();
	}

}
