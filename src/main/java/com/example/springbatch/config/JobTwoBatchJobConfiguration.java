package com.example.springbatch.config;

import com.example.springbatch.listners.JobTwoListener;
import com.example.springbatch.processor.JobTwoProcessor;
import com.example.springbatch.reader.JobTwoReader;
import com.example.springbatch.utils.Constants;
import com.example.springbatch.writer.JobTwoWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

// https://docs.spring.io/spring-batch/reference/step/chunk-oriented-processing/configuring.html

@Configuration(Constants.JobName.TEST_JOB_TWO)
public class JobTwoBatchJobConfiguration {

    Logger LOGGER = LoggerFactory.getLogger(JobTwoBatchJobConfiguration.class);
    /**
     * Note the JobRepository is typically autowired in and not needed to be explicitly
     * configured
     */
    @Bean
    public Job jobTwo(JobRepository jobRepository, Step jobTwoStep) {
        return new JobBuilder("jobTwo", jobRepository)
                .start(jobTwoStep).listener(new JobTwoListener())
                .build();
    }

    /**
     * Note the TransactionManager is typically autowired in and not needed to be explicitly
     * configured
     */
    @Bean
    public Step jobTwoStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager) {
        return new StepBuilder("jobTwo", jobRepository)
                .<String, String>chunk(1, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    private ItemWriter<String> writer() {
        JobTwoWriter writer = new JobTwoWriter();
        return writer;
    }

    private ItemProcessor<String, String> processor() {
        JobTwoProcessor processor = new JobTwoProcessor();
        return processor;
    }

    private ItemReader<String> reader() {
        JobTwoReader reader =  new JobTwoReader();
        return reader;
    }
}
