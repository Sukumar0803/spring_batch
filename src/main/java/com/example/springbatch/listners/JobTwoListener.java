package com.example.springbatch.listners;

import com.example.springbatch.config.JobTwoBatchJobConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;

// There Are Lot of Listners
// https://howtodoinjava.com/spring-batch/spring-batch-event-listeners/

public class JobTwoListener implements JobExecutionListener {

    Logger LOGGER = LoggerFactory.getLogger(JobTwoListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("Job started at: " + jobExecution.getStartTime());
        // Add any setup or logic before the job starts
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("Job finished at: " + jobExecution.getEndTime());
        // Add any cleanup or logic after the job completes
    }

}
