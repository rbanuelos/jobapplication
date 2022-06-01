package com.example.jobapplication.service;

import com.example.jobapplication.model.JobOffer;
import com.example.jobapplication.repository.JobOfferRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JobOfferServiceImpl implements JobOfferService {

  private final JobOfferRepository jobOfferRepository;

  public JobOfferServiceImpl(JobOfferRepository jobOfferRepository) {
    this.jobOfferRepository = jobOfferRepository;
  }

  @Override
  public List<JobOffer> getJobApplications() {
    List<JobOffer> jobOffers = new ArrayList<>();
    this.jobOfferRepository
        .findAll()
        .forEach(jobApplicationEntity -> {
          JobOffer jobOffer = new JobOffer(
              jobApplicationEntity.getId(),
              jobApplicationEntity.getTitle(),
              jobApplicationEntity.getDescription(),
              jobApplicationEntity.getTags());
          jobOffers.add(jobOffer);
        });
    return jobOffers;
  }
}
