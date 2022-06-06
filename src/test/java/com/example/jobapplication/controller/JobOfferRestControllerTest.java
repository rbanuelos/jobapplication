package com.example.jobapplication.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.jobapplication.entity.JobOfferEntity;
import com.example.jobapplication.repository.JobOfferRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JobOfferRestControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  JobOfferRepository jobOfferRepository;

  @Test
  public void getAllJobOffers() throws Exception {
    List<JobOfferEntity> jobOfferEntities = new ArrayList<>();
    jobOfferEntities.add(new JobOfferEntity(
        "Test 1",
        "Test 1",
        "tag1,tag2"));

    Mockito.when(jobOfferRepository.findAll()).thenReturn(jobOfferEntities);

    mockMvc.perform(MockMvcRequestBuilders
            .get("/jobs")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
  }

  @Test
  public void getAllJobOffersExistingByTag() throws Exception {
    List<JobOfferEntity> jobOfferEntities = new ArrayList<>();
    jobOfferEntities.add(new JobOfferEntity(
        "Test 1",
        "Test 1",
        "tag1,tag2"));

    jobOfferEntities.add(new JobOfferEntity(
        "Test 1",
        "Test 1",
        "tag1,tag3"));


    Mockito.when(jobOfferRepository.findAll()).thenReturn(jobOfferEntities);

    mockMvc.perform(MockMvcRequestBuilders
            .get("/jobs")
            .param("tags", "tag1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  public void getAllJobOffersByNonExistingTag() throws Exception {
    List<JobOfferEntity> jobOfferEntities = new ArrayList<>();
    jobOfferEntities.add(new JobOfferEntity(
        "Test 1",
        "Test 1",
        "tag1,tag2"));

    jobOfferEntities.add(new JobOfferEntity(
        "Test 1",
        "Test 1",
        "tag1,tag3"));


    Mockito.when(jobOfferRepository.findAll()).thenReturn(jobOfferEntities);

    mockMvc.perform(MockMvcRequestBuilders
            .get("/jobs?tags=tag4")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
  }

}
