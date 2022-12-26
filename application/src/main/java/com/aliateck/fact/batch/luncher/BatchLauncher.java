package com.aliateck.fact.batch.luncher;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

//@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BatchLauncher {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

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
