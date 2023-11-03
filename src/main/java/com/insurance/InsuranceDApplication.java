package com.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories(basePackages = "com.insurance.repository")
@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
@EnableEurekaClient
public class InsuranceDApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceDApplication.class, args);
	}

}
