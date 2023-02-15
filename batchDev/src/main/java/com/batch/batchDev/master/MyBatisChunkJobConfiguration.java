package com.batch.batchDev.master;

import com.batch.batchDev.service.vo.NllpVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.batch.batchDev.master
 * fileName       : MyBatisChunkJobConfiguration
 * author         : hyeokchan
 * date           : 2023/02/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/02/15        hyeokchan       최초 생성
 */
@Configuration
@Slf4j
public class MyBatisChunkJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public MyBatisChunkJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    private int chunkSize = 5;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Bean
    public Job myBatisChunkJob() {
        return jobBuilderFactory.get("myBatisChunkJob")
                .incrementer(new RunIdIncrementer()) // 자동 생성
                .start(myBatisChunkJobStep01())
                .build();
    }

    @Bean
    public Step myBatisChunkJobStep01() {
        return stepBuilderFactory.get("myBatisChunkJobStep01")
                .<NllpVO, NllpVO>chunk(chunkSize)
                .reader(myBatisItemReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    @StepScope
    public MyBatisPagingItemReader<NllpVO> myBatisItemReader() {
        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("landAr", "0");
        return new MyBatisPagingItemReaderBuilder<NllpVO>()
                .pageSize(chunkSize)
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.batch.batchDev.service.mapper.BatchMapper.findNllpList")
                .parameterValues(parameterValues)
                .build();
    }

    private ItemWriter<? super NllpVO> itemWriter() {
        return items -> {
            log.info("nllpVO :::: {}", items.size());
        };
    }
}
