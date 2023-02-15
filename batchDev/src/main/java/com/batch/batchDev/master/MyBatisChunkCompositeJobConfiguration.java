package com.batch.batchDev.master;

import com.batch.batchDev.master.writer.InstItemWriter;
import com.batch.batchDev.master.writer.UpdtItemWriter;
import com.batch.batchDev.service.vo.NllpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.print.attribute.standard.JobName;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.batch.batchDev.master
 * fileName       : MyBatisChunkCompositeJobConfiguration
 * author         : hyeokchan
 * date           : 2023/02/16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/02/16        hyeokchan       최초 생성
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class MyBatisChunkCompositeJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private int chunkSize = 5;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    private final String JOB_NAME = "myBatisChunkComposite";

    @Autowired
    UpdtItemWriter updtItemWriter;

    @Autowired
    InstItemWriter instItemWriter;

    @Bean(JOB_NAME)
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer()) // 자동 생성
                .start(step01())
                .build();
    }

    @Bean(JOB_NAME+"Step01")
    public Step step01() {
        return stepBuilderFactory.get(JOB_NAME+"Step01")
                .<NllpVO, NllpVO>chunk(chunkSize)
                .reader(itemReader())
                .writer(compositeItemWriter())
                .build();
    }

    @Bean(JOB_NAME+"ItemReader")
    @StepScope
    public MyBatisPagingItemReader<NllpVO> itemReader() {
        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("landAr", "0");
        return new MyBatisPagingItemReaderBuilder<NllpVO>()
                .pageSize(chunkSize)
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.batch.batchDev.service.mapper.BatchMapper.findNllpList")
                .parameterValues(parameterValues)
                .build();
    }

    @Bean(JOB_NAME+"CompositeItemWriter")
    @StepScope
    public CompositeItemWriter<NllpVO> compositeItemWriter() {
        final CompositeItemWriter<NllpVO> compositeItemWriter = new CompositeItemWriter<>();
//        compositeItemWriter.setDelegates(Arrays.asList(myBatisItemWriter01(), myBatisItemWriter02()));
//        compositeItemWriter.setDelegates(Arrays.asList(myBatisItemWriter01(), updtItemWriter));
        compositeItemWriter.setDelegates(Arrays.asList(instItemWriter, updtItemWriter));
        return compositeItemWriter;
    }

    @Bean(JOB_NAME+"myBatisItemWriter01")
    @StepScope
    public MyBatisBatchItemWriter<NllpVO> myBatisItemWriter01() {
        return new MyBatisBatchItemWriterBuilder<NllpVO>()
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("com.batch.batchDev.service.mapper.BatchMapper.instNllpInfo")
                .build();
    }

    @Bean(JOB_NAME+"myBatisItemWriter02")
    @StepScope
    public MyBatisBatchItemWriter<NllpVO> myBatisItemWriter02() {
        return new MyBatisBatchItemWriterBuilder<NllpVO>()
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("com.batch.batchDev.service.mapper.BatchMapper.updtNllpInfo")
                .build();
    }


}
