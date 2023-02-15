package com.batch.batchDev.master;

import com.batch.batchDev.service.vo.NllpVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.batch.batchDev
 * fileName       : ChunkJobConfiguration
 * author         : hyeokchan
 * date           : 2023/02/15
 * description    : 청크 방식 배치
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/02/15        hyeokchan       최초 생성
 */
@Configuration
@Slf4j
public class ChunkJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public ChunkJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job chunkJob() {
        return jobBuilderFactory.get("chunkJob")
                .incrementer(new RunIdIncrementer()) // 자동 생성
                .start(chunkJobStep01())
                .build();
    }

    @Bean
    public Step chunkJobStep01() {
        return stepBuilderFactory.get("chunkJobStep01")
                .<String, String>chunk(5)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    private ItemReader<String> itemReader() {
        return new ListItemReader<>(getItems());
    }
    private ItemProcessor<String, String> itemProcessor() {
        return item -> item + ", Spring Batch";
    }
    private ItemWriter<String> itemWriter() {
        return items -> log.info("chunk item size : {}", items.size());
    }

    public List<String> getItems() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add(i + " Hello");
        }
        return items;
    }


}
