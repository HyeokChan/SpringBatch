package com.batch.batchDev;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 기본 스프링부트 프로젝트에서 스프링 배치 사용을 위해 어노테이션 추가(JobBuilderFactory, StepBuilderFactory)
@EnableBatchProcessing
public class BatchDevApplication {
	public static void main(String[] args) {
		SpringApplication.run(BatchDevApplication.class, args);
	}

}
