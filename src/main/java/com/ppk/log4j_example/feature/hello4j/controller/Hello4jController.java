package com.ppk.log4j_example.feature.hello4j.controller;

import com.ascendcorp.logger.log4j.ACNLogger;
import com.ppk.log4j_example.feature.hello4j.service.DisplayPasswordService;
import com.ppk.log4j_example.feature.hello4j.service.DisplayUserNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class Hello4jController {

    private static final ACNLogger LOGGER = ACNLogger.create(Hello4jController.class);

    @Autowired private DisplayUserNameService displayUserNameService;
    @Autowired private DisplayPasswordService displayPasswordService;

    @GetMapping(value = "/test1")
    private ResponseEntity<String> initHello4j(@RequestParam("requestTrans") String requestTrans){
        LOGGER.info("{}","Hello4j");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/test2")
    private ResponseEntity<String> initHello4j2(@RequestParam("requestTrans") String requestTrans) throws Exception{
        LOGGER.info("{}","Hello4j");
        CompletableFuture<String> username = this.displayUserNameService.display();
        CompletableFuture<String> password = this.displayPasswordService.display();

        CompletableFuture.allOf(username,password).get();

        LOGGER.info("Your Username : [{}]",username.toString());
        LOGGER.info("Your Password : [{}]",password.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
