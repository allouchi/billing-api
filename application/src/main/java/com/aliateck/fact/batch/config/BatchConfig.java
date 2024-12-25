package com.aliateck.fact.batch.config;

import com.aliateck.fact.batch.items.FactureProcessor;
import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.fact.domaine.ports.api.batch.BatchApiService;
import com.aliateck.fact.infrastructure.mapper.FactureMapper;
import com.aliateck.fact.infrastructure.repository.facture.FactureJpaRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.ListItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

//@Service
//@EnableBatchProcessing
//@EnableScheduling
@NoArgsConstructor
@Slf4j
public class BatchConfig {

    private static final String JOB_NAME = "listFacturesJob";
    private static final String STEP_NAME = "processingStep";

    private StepBuilder stepBuilder;
    private JobBuilder jobBuilder;
    private BatchApiService batchApiService;
    private FactureJpaRepository factureJpaRepository;
    private FactureMapper factureMapper;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;


    @Bean
    public Step factureStep() {
        /*
        return new StepBuilder(STEP_NAME, jobRepository).<String, String>chunk(2, transactionManager)
                .reader(factureItemReader())
                .writer(factureItemWriter())
                .build(); */
        return null;

    }

    @Bean
    public Job listFacturesJob(Step step1) {

        //return jobBuilder.get(JOB_NAME).start(step1).build();
        return null;
    }

    @Bean
    public ItemProcessor<Facture, Facture> factureItemProcessor() {
        return new FactureProcessor(batchApiService);
    }

    @Bean
    public ListItemReader<Facture> factureItemReader() {
        //return new FactureReader(factureJpaRepository, factureMapper);
        return null;
    }

    @Bean
    public ListItemWriter<Facture> factureItemWriter() {
        //return new FactureWriter(batchApiService);
        return null;
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
