package com.example.springbatch.config;

import com.example.springbatch.utils.Constants;
import com.example.springbatch.utils.JobTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

// https://www.javainuse.com/spring/springbatchtasklet

@Component
public class TaskletJobConfig implements Tasklet {

    private Logger LOGGER = LoggerFactory.getLogger(TaskletJobConfig.class);

    @Autowired
    private BeanFactory beanFactory;

    @Bean
    public Job taskletJob(JobRepository jobRepository, Step taskletStep) {
        return new JobBuilder("taskletJob", jobRepository)
                .start(taskletStep)
                .build();
    }

    @Bean
    public Step taskletStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("taskletStep", jobRepository).tasklet(this, transactionManager).build();
    }

    /**
     * @param contribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("Hello Task Job Started..! Hurray");
        try {
            JobTask task = beanFactory.getBean(System.getenv(Constants.JOB_TYPE), JobTask.class);
            task.execute();
        } catch (Exception e) {
            LOGGER.info("{} has been failed due to {}", System.getenv(Constants.JOB_TYPE), e.getMessage());
        }
        return RepeatStatus.FINISHED;
    }
}
