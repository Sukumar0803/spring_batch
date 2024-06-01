package com.example.springbatch.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class JobTwoWriter implements ItemWriter<String> {

    Logger LOGGER = LoggerFactory.getLogger(JobTwoWriter.class);
    /**
     * @param chunk
     * @throws Exception
     */
    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        LOGGER.info("{}", chunk.getItems());
    }
}
