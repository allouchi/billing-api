package com.aliateck.fact.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@ComponentScan(basePackages =  {		
		"com.aliateck.fact.domaine.adapter",
		"com.aliateck.fact.domaine.ports.spi",
		"com.aliateck.fact.domaine.ports.api",
		"com.aliateck.fact.infrastructure.adapter",
		"com.aliateck.fact.infrastructure.mapper", 
		"com.aliateck.fact.application.controllers"})
@EntityScan("com.aliateck.fact.infrastructure.models")
@EnableJpaRepositories("com.aliateck.fact.infrastructure.repository")
public class FactApiApplicationLayerApplication {

    public static void main(String[] args) {
    	SpringApplication.run(FactApiApplicationLayerApplication.class, args);       
        
    }

}
