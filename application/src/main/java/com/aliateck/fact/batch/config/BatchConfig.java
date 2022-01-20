package com.aliateck.fact.batch.config;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.aliateck.fact.batch.items.FactureProcessor;
import com.aliateck.fact.batch.items.FactureReader;
import com.aliateck.fact.batch.items.FactureWriter;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.batch.BatchApiService;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@EnableBatchProcessing
// @EnableScheduling
@NoArgsConstructor
@Slf4j
public class BatchConfig {

	private static final String JOB_NAME = "listFacturesJob";
	private static final String STEP_NAME = "processingStep";

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private BatchApiService batchApiService;
	@Autowired
	private FactureJpaRepository factureJpaRepository;
	@Autowired
	private FactureMapper factureMapper;

	@Bean
	public Step factureStep() {
		return stepBuilderFactory.get(STEP_NAME).<Facture, Facture> chunk(5).reader(factureItemReader())
				.processor(factureItemProcessor()).writer(factureItemWriter()).build();
	}

	@Bean
	public Job listFacturesJob(Step step1) {
		return jobBuilderFactory.get(JOB_NAME).start(step1).build();
	}

	@Bean
	public ItemProcessor<Facture, Facture> factureItemProcessor() {
		return new FactureProcessor(batchApiService);
	}

	@Bean
	public ItemReader<Facture> factureItemReader() {
		return new FactureReader(factureJpaRepository, factureMapper);
	}

	@Bean
	public ItemWriter<Facture> factureItemWriter() {
		return new FactureWriter(batchApiService);
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobExecutionListener() {

			@Override
			public void beforeJob(JobExecution jobExecution) {
				/**
				 * As of now empty but can add some before job conditions
				 */
			}

			@Override
			public void afterJob(JobExecution jobExecution) {
				if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
					log.info("!!! JOB FINISHED! Time to verify the results");
					batchApiService.findAllFactures()
							.forEach(facture -> log.info("Found <" + facture.getMoisFacture() + "> in the database."));
				}
			}
		};
	}

}
