package com.boot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootInitialProjectApplication implements ApplicationRunner {

    public static void main(final String[] args) {
        SpringApplication.run(SpringBootInitialProjectApplication.class, args);
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {

    }
}