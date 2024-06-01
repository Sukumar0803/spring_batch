package com.example.springbatch;


import com.example.springbatch.utils.Constants;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@EnableBatchProcessing
public class SpringbatchApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbatchApplication.class);

    public static void main(String[] args) {

        LOGGER.info("LOGIC Started");
        run(args);
        System.exit(0);
    }

    private static void run(String[] args) {
        final long startTime = System.currentTimeMillis();
        LOGGER.info("loading the context for the SpringbatchApplication:: to retireve further bean associated to this calss");
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringbatchApplication.class, args);

        LOGGER.info("Loading jobLauncher in our context:");
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);

        JobParameters jobParameters = new JobParametersBuilder().addDate("Date", new Date())
                .addString(Constants.BatchConstants.TRACE_ID, UUID.randomUUID().toString()).toJobParameters();

        LOGGER.info("Input Parameter: {}", System.getenv(Constants.JOB_TYPE));
        String jobName = null;

        if (System.getenv(Constants.JOB_TYPE) != null) {
            if (System.getenv(Constants.JOB_TYPE).contains("TASK"))
                jobName = "taskletJob";
            else if (System.getenv(Constants.JOB_TYPE).equalsIgnoreCase(Constants.JobName.TEST_JOB_TWO))
                jobName = "jobTwo";
            else {
                LOGGER.info("Invalid JobName");
            }
        }

        if (StringUtils.isNotBlank(jobName)) {
            try {
                Job jobToStart = ctx.getBean(jobName, Job.class);
                JobExecution jobExecution = jobLauncher.run(jobToStart, jobParameters);
                //https://www.baeldung.com/introduction-to-spring-batch
                LOGGER.info("Job Id :{}", jobExecution.getJobId());
                LOGGER.info("Job Status :{}", jobExecution.getStatus());
                final long endTime = System.currentTimeMillis();
                LOGGER.info("TIME Taken:{}", endTime - startTime);
            } catch (Exception e) {
                LOGGER.info("An Exception occurred due to {}", e.getMessage());
                System.exit(0);
            }
        }
        LOGGER.info("Execution Stopped due to job Name is null..");
    }

}
