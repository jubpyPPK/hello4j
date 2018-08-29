package com.ppk.log4j_example.feature.hello4j.service;

import com.ascendcorp.logger.log4j.ACNLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DisplayPasswordService {

    private static final ACNLogger LOGGER = ACNLogger.create(DisplayPasswordService.class);

    @Async
    public CompletableFuture<String> display(){
        long bg = System.currentTimeMillis();
        try {
            return CompletableFuture.completedFuture("jubjub55");
        }finally {
            LOGGER.info("Total time for DisplayPasswordService Task : " + (System.currentTimeMillis()-bg));
        }
    }

}
