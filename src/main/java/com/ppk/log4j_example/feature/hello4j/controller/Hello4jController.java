package com.ppk.log4j_example.feature.hello4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Hello4jController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hello4jController.class);

    private static final String LOG_CORRELATIONID_KEY = "correlationId";
    private static final String LOG_TRANS_KEY = "transId";

    @GetMapping(value = "/test1")
    private ResponseEntity<String> initHello4j(@RequestParam("requestTrans") String requestTrans){
        try {
            MDC.put(LOG_CORRELATIONID_KEY,this.generateCorrelationId());
            MDC.put(LOG_TRANS_KEY,requestTrans);
            LOGGER.info("{}","Hello4j");
            return new ResponseEntity<>(HttpStatus.OK);
        }finally {
            MDC.remove(LOG_CORRELATIONID_KEY);
            MDC.clear();
        }
    }

    private String generateCorrelationId(){
        return UUID.randomUUID().toString();
    }

}
