package com.ppk.log4j_example;

import com.ppk.log4j_example.common.config.ExampleTaskDecorator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@SpringBootApplication
public class Log4jExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Log4jExampleApplication.class, args);
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.initialize();
		executor.setTaskDecorator(new ExampleTaskDecorator());
		return executor;
	}

}
