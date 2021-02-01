package com.aliateck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BatchStarter {	

	public static void main(String[] args) {
		SpringApplication.run(BatchStarter.class, args);
	}	

}
