package com.ppk.log4j_example.common.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Hello4jUtils {

    public String generateCorrelationId(){
        return UUID.randomUUID().toString();
    }

}
