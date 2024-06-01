package com.example.springbatch.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.concurrent.atomic.AtomicBoolean;

public class JobTwoReader implements ItemReader<String> {

    Logger LOGGER = LoggerFactory.getLogger(JobTwoReader.class);

    private int value = 0;
    /**
     * @return
     * @throws Exception
     * @throws UnexpectedInputException
     * @throws ParseException
     * @throws NonTransientResourceException
     */
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        LOGGER.info("Reader Executed...!");
//        if (value == 0)
//            return "Hello Iam Reader";
        return null;
    }
}
