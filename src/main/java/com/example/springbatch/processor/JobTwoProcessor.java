package com.example.springbatch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class JobTwoProcessor implements ItemProcessor<String, String> {
    Logger LOGGER = LoggerFactory.getLogger(JobTwoProcessor.class);
    /**
     * @param item
     * @return
     * @throws Exception
     */
    @Override
    public String process(String item) throws Exception {
        LOGGER.info("processor Executed...!");
        return item+ " Processor Also Started";
    }
}
