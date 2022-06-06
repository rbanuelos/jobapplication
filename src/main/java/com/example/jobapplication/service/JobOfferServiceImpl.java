package com.example.jobapplication.service;

import com.example.jobapplication.entity.JobApplicationEntity;
import com.example.jobapplication.entity.JobOfferEntity;
import com.example.jobapplication.model.JobApplication;
import com.example.jobapplication.model.JobOffer;
import com.example.jobapplication.repository.JobApplicationRepository;
import com.example.jobapplication.repository.JobOfferRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Service implementation for job offers and applications.
 */
@Service
public class JobOfferServiceImpl implements JobOfferService {

  private final JobOfferRepository jobOfferRepository;
  private final JobApplicationRepository jobApplicationRepository;

  public JobOfferServiceImpl(JobOfferRepository jobOfferRepository,
                             JobApplicationRepository jobApplicationRepository) {
    this.jobOfferRepository = jobOfferRepository;
    this.jobApplicationRepository = jobApplicationRepository;
  }

  /**
   * Service method to get job offers. This is the only cacheable method in the app. It uses the
   * tags parameter to save results for that particular search.
   *
   * @param tags list of tags to filter results
   * @return list of job offers
   */
  @Override
  @Cacheable(value = "getJobOffers", key = "#tags")
  public List<JobOffer> getJobOffers(List<String> tags) {
    System.out.println("Getting list of Job Offers...");
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

  /**
   * Add a new Job Offer to the DB.
   *
   * @param jobOffer a new Job Offer
   * @return the identifier of the new entity created
   */
  @Override
  public Long addJobOffer(JobOffer jobOffer) {
    return this.jobOfferRepository.save(
        this.getEntityFromJobOffer(jobOffer)
    ).getId();
  }

  @Override
  @CacheEvict(value = "getJobApplications", key = "#jobOfferId")
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

  @Override
  @Cacheable(value = "getJobApplications", key = "#jobOfferId")
  public List<JobApplication> getJobApplications(Long jobOfferId) {
    return this.jobApplicationRepository.findByJobOfferId(jobOfferId)
        .stream()
        .map(this::getJobApplicationFromEntity).collect(
            Collectors.toList());
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

  private JobApplication getJobApplicationFromEntity(JobApplicationEntity jobApplicationEntity) {
    return new JobApplication(
        jobApplicationEntity.getFullName(),
        jobApplicationEntity.getAddress(),
        jobApplicationEntity.getPhone()
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
