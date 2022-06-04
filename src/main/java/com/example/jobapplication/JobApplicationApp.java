package com.example.jobapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main Spring Boot app.
 */
@SpringBootApplication
@EnableCaching
public class JobApplicationApp {

  public static void main(String[] args) {
    SpringApplication.run(JobApplicationApp.class, args);
  }

}
