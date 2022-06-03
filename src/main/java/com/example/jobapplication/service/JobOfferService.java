package com.example.jobapplication.service;

import com.example.jobapplication.model.JobOffer;
import java.util.List;

public interface JobOfferService {
  List<JobOffer> getJobOffers(List<String> tags);

  JobOffer getJobOffer(Long id);

}
