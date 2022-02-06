package com.aliateck.fact.batch.luncher;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BatchLauncher {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public BatchStatus run() {
		log.info("Step démarré");
		JobExecution jobExecution = null;

		try {
//			JobParameters parameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
//			.toJobParameters();

			JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date())
					.addLong("time", System.currentTimeMillis()).toJobParameters();

			jobExecution = jobLauncher.run(job, jobParameters);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jobExecution.getStatus();

	}

}
