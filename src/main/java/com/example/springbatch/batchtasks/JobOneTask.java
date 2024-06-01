package com.example.springbatch.batchtasks;

import com.example.springbatch.utils.JobTask;
import com.example.springbatch.config.TaskletJobConfig;
import com.example.springbatch.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration(Constants.JobName.TEST_JOB_ONE)
public class JobOneTask implements JobTask {

    private Logger LOGGER = LoggerFactory.getLogger(TaskletJobConfig.class);

    /**
     *
     */
    @Override
    public void execute() {
        LOGGER.info("My job has been started Successfully: {}", System.getenv(Constants.JOB_TYPE));
    }
}
