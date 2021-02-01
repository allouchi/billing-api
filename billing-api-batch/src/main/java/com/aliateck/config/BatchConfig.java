package com.aliateck.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.aliateck.fact.domaine.business.object.Facture;
import com.aliateck.items.FactureProcessor;
import com.aliateck.items.FactureWriter;



@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {
	
	private static final String JOB_NAME = "listFacturesJob";
    private static final String STEP_NAME = "processingStep";
    private static final String READER_NAME = "facturesItemReader";
    
	
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step factureStep() {
        return stepBuilderFactory.get(STEP_NAME)
                .<Facture, Facture>chunk(5)
                .reader(factureItemReader())
                .processor(factureItemProcessor())
                .writer(factureItemWriter())
                .build();
    }

    @Bean
    public Job listFacturesJob(Step step1) {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step1)
                .build();
    }

    @Bean
    public ItemReader<Facture> factureItemReader() {
        FlatFileItemReader<Facture> reader = new FlatFileItemReader<>();
       
        return reader;

    }
    
    @Bean
    public ItemProcessor<Facture, Facture> factureItemProcessor() {
        return new FactureProcessor();
    }
    
    @Bean
    public ItemWriter<Facture> factureItemWriter() {
        return new FactureWriter();
    }

}
