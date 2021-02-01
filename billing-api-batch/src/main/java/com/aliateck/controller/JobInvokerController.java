package com.aliateck.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliateck.launcher.BatchLauncher;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class JobInvokerController {

	@Autowired
	private BatchLauncher batchLauncher;

	@PostMapping
	public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {
		log.info("Batch demarré à la demande");
		return batchLauncher.run();

	}
}
