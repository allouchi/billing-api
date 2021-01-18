package com.aliateck;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aliateck.fact.domaine.ports.api.batch.BatchApiService;

@SpringBootApplication
@EnableBatchProcessing
public class BatchStarter {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	 private BatchApiService batchApiService;

	public static void main(String[] args) {
		SpringApplication.run(BatchStarter.class, args);
	}
	/*
	@Bean
	public JobRepository jobRepositoryObj() throws Exception {
		JobRepositoryFactoryBean jobRepoFactory = new JobRepositoryFactoryBean();
		jobRepoFactory.setTransactionManager(transactionManager);
		jobRepoFactory.setDataSource(dataSource);
		return jobRepoFactory.getObject();
	}
	
	
	
	
	@Bean
	public Step batchStep() {
		return stepBuilderFactory.get("stepDatawarehouseLoader")
				.build();
	}

	@Bean
	public Job jobStep() throws Exception {
		return jobBuilderFactory.get("jobDatawarehouseLoader")
				.repository(jobRepositoryObj())
				.incrementer(new RunIdIncrementer())
				.listener(batchJobListener())
				.flow(batchStep()).end().build();
	}
	*/

}
