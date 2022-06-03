package com.example.jobapplication.service;

import com.example.jobapplication.entity.JobApplicationEntity;
import com.example.jobapplication.entity.JobOfferEntity;
import com.example.jobapplication.model.JobApplication;
import com.example.jobapplication.model.JobOffer;
import com.example.jobapplication.repository.JobApplicationRepository;
import com.example.jobapplication.repository.JobOfferRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JobOfferServiceImpl implements JobOfferService {

  private final JobOfferRepository jobOfferRepository;
  private final JobApplicationRepository jobApplicationRepository;

  public JobOfferServiceImpl(JobOfferRepository jobOfferRepository,
                             JobApplicationRepository jobApplicationRepository) {
    this.jobOfferRepository = jobOfferRepository;
    this.jobApplicationRepository = jobApplicationRepository;
  }

  @Override
  public List<JobOffer> getJobOffers(List<String> tags) {
    List<JobOffer> jobOffers = new ArrayList<>();
    this.jobOfferRepository
        .findAll()
        .forEach(jobOfferEntity -> {
          boolean containsTag =
              tags.stream().anyMatch(tag -> jobOfferEntity.getTags().contains(tag));

          JobOffer jobOffer = this.getJobOfferFromEntity(jobOfferEntity);

          if (tags.isEmpty() || containsTag) {
            jobOffers.add(jobOffer);
          }
        });
    return jobOffers;
  }

  @Override
  public JobOffer getJobOffer(Long id) {
    return this.jobOfferRepository
        .findById(id)
        .map(this::getJobOfferFromEntity)
        .orElse(null);
  }

  @Override
  public Long addJobOffer(JobOffer jobOffer) {
    return this.jobOfferRepository.save(
        this.getEntityFromJobOffer(jobOffer)
    ).getId();
  }

  @Override
  public boolean addJobApplication(Long jobOfferId, JobApplication jobApplication) {
    JobOfferEntity jobOfferEntity = this.jobOfferRepository.findById(jobOfferId).orElse(null);

    if (jobOfferEntity == null) {
      return false;
    }

    JobApplicationEntity jobApplicationEntity = this.getEntityFromJobApplication(jobApplication);
    jobApplicationEntity.setJobOffer(jobOfferEntity);

    this.jobApplicationRepository.save(jobApplicationEntity);

    return true;
  }

  private JobOffer getJobOfferFromEntity(JobOfferEntity jobOfferEntity) {
    return new JobOffer(
        jobOfferEntity.getId(),
        jobOfferEntity.getTitle(),
        jobOfferEntity.getDescription(),
        jobOfferEntity.getTags(),
        jobOfferEntity.getJobApplications().size(),
        jobOfferEntity.isClosed()
    );
  }

  private JobOfferEntity getEntityFromJobOffer(JobOffer jobOffer) {
    return new JobOfferEntity(
        jobOffer.getTitle(),
        jobOffer.getDescription(),
        jobOffer.getTags()
    );
  }

  private JobApplicationEntity getEntityFromJobApplication(JobApplication jobApplication) {
    return new JobApplicationEntity(
        jobApplication.getFullName(),
        jobApplication.getAddress(),
        jobApplication.getPhone()
    );
  }
}
