package com.batch.batchDev.master;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.batch.batchDev
 * fileName       : LogJob
 * author         : hyeokchan
 * date           : 2023/02/15
 * description    : 태스클릿 방식으로 로그를 찍는 배치
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/02/15        hyeokchan       최초 생성
 */

@Configuration
@Slf4j
public class LogJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public LogJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job logJob() {
        return jobBuilderFactory.get("logJob")
                .incrementer(new RunIdIncrementer()) // 자동 생성
                .start(step01())
                .next(step02())
                .build();
    }

    @Bean
    public Step step01() {
        return stepBuilderFactory.get("step01")
                .tasklet((contribution, chunkContext) -> {
                    log.info("process step01");
                    // Tasklet이 완료되었음을 스프링 배치에게 알림
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step02() {
        return stepBuilderFactory.get("step02")
                .tasklet((contribution, chunkContext) -> {
                    log.info("process step02");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
