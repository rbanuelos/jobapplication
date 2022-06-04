package com.example.jobapplication.controller;

import com.example.jobapplication.model.JobApplication;
import com.example.jobapplication.model.JobOffer;
import com.example.jobapplication.service.JobOfferService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for Job Offer and Job Application APIs.
 */
@RestController
@RequestMapping(value = "/jobs")
public class JobOfferRestController {

  private final JobOfferService jobOfferService;

  public JobOfferRestController(JobOfferService jobOfferService) {
    this.jobOfferService = jobOfferService;
  }

  /**
   * GET method for getting all job offers that are associated to any of the provided tags.
   *
   * @param tags query parameter - comma separated tag values
   * @return a list of Job Offers
   */
  @GetMapping()
  public ResponseEntity<List<JobOffer>> getAllJobOffers(@Param("tags") String tags) {
    List<String> tagList;
    if (tags == null) {
      tagList = new ArrayList<>();
    } else {
      tagList = Stream.of(tags.split(","))
          .map(String::trim)
          .collect(Collectors.toList());
    }

    return new ResponseEntity<>(
        this.jobOfferService.getJobOffers(tagList),
        HttpStatus.OK
    );
  }

  /**
   * GET method for getting a job offers.
   *
   * @param id path parameter - job offer identifier
   * @return a Job Offers
   */
  @GetMapping("/{jobOfferId}")
  public ResponseEntity<JobOffer> getJobOffer(@PathVariable("jobOfferId") Long id) {
    JobOffer jobOffer = this.jobOfferService.getJobOffer(id);

    if (jobOffer == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(
        jobOffer,
        HttpStatus.OK
    );
  }

  /**
   * POST method for adding a new Job Offer.
   *
   * @param jobOffer a request body with title, description and tags
   * @return - Integer - the new inserted id
   */
  @PostMapping()
  public ResponseEntity<Long> addJobOffer(@RequestBody JobOffer jobOffer) {
    return new ResponseEntity<>(
        this.jobOfferService.addJobOffer(jobOffer),
        HttpStatus.CREATED
    );
  }

  /**
   * POST method for adding a new job application to an existing job offer.
   *
   * @param jobOfferId     job offer identifier
   * @param jobApplication request body containing job application data
   * @return true if new application was successfully added, false otherwise
   */
  @PostMapping("/{jobOfferId}/apply")
  public ResponseEntity<Boolean> addJobApplication(@PathVariable Long jobOfferId,
                                                   @RequestBody JobApplication jobApplication) {
    return new ResponseEntity<>(
        this.jobOfferService.addJobApplication(jobOfferId, jobApplication),
        HttpStatus.CREATED
    );
  }

  /**
   * GET method for getting the list of applications for a specific job.
   *
   * @param jobOfferId job offer identifier
   * @return a list of job applications
   */
  @GetMapping("/{jobOfferId}/applications")
  public ResponseEntity<List<JobApplication>> getJobOfferApplications(
      @PathVariable("jobOfferId") Long jobOfferId) {
    JobOffer jobOffer = this.jobOfferService.getJobOffer(jobOfferId);

    if (jobOffer == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(
        this.jobOfferService.getJobApplications(jobOfferId),
        HttpStatus.OK
    );
  }
}
