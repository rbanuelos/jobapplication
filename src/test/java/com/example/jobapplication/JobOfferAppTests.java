package com.example.jobapplication;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.jobapplication.controller.JobOfferRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobOfferAppTests {

  @Autowired
  private JobOfferRestController jobOfferRestController;
  @Test
  void contextLoads() {
    assertThat(jobOfferRestController).isNotNull();
  }

}
