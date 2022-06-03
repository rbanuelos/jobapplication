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

@RestController
@RequestMapping(value = "/jobs")
public class JobOfferRestController {

  private final JobOfferService jobOfferService;

  public JobOfferRestController(JobOfferService jobOfferService) {
    this.jobOfferService = jobOfferService;
  }

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

  @PostMapping()
  public ResponseEntity<Long> addJobOffer(@RequestBody JobOffer jobOffer) {
    return new ResponseEntity<>(
        this.jobOfferService.addJobOffer(jobOffer),
        HttpStatus.CREATED
    );
  }

  @PostMapping("/{jobOfferId}/apply")
  public ResponseEntity<Boolean> addJobApplication(@PathVariable Long jobOfferId,
                                          @RequestBody JobApplication jobApplication) {
    return new ResponseEntity<>(
        this.jobOfferService.addJobApplication(jobOfferId, jobApplication),
        HttpStatus.CREATED
    );
  }
}
