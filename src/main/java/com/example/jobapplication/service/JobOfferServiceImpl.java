package com.example.jobapplication.service;

import com.example.jobapplication.entity.JobOfferEntity;
import com.example.jobapplication.model.JobOffer;
import com.example.jobapplication.repository.JobOfferRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JobOfferServiceImpl implements JobOfferService {

  private JobOfferRepository jobOfferRepository;

  public JobOfferServiceImpl(JobOfferRepository jobOfferRepository) {
    this.jobOfferRepository = jobOfferRepository;
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

  private JobOffer getJobOfferFromEntity(JobOfferEntity jobOfferEntity) {
    return new JobOffer(
        jobOfferEntity.getId(),
        jobOfferEntity.getTitle(),
        jobOfferEntity.getDescription(),
        jobOfferEntity.getTags(),
        jobOfferEntity.getJobApplications().size()
    );
  }
}
