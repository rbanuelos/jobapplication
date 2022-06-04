package com.example.jobapplication.service;

import com.example.jobapplication.model.JobApplication;
import com.example.jobapplication.model.JobOffer;
import java.util.List;

/**
 * Service interface that defines basic methods for CRUD operations for job offers and applications.
 */
public interface JobOfferService {
  List<JobOffer> getJobOffers(List<String> tags);

  JobOffer getJobOffer(Long id);

  Long addJobOffer(JobOffer jobOffer);

  boolean addJobApplication(Long jobOfferId, JobApplication jobApplication);

  List<JobApplication> getJobApplications(Long jobOfferId);
}
