package com.queueservice.taxapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
public class TaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxApplication.class, args);
	}

}
