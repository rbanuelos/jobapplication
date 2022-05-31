package com.example.jobapplication.service;

import com.example.jobapplication.model.JobApplication;
import com.example.jobapplication.repository.JobApplicationRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

  private final JobApplicationRepository jobApplicationRepository;

  public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository) {
    this.jobApplicationRepository = jobApplicationRepository;
  }

  @Override
  public List<JobApplication> getJobApplications() {
    List<JobApplication> jobApplications = new ArrayList<>();
    this.jobApplicationRepository
        .findAll()
        .forEach(jobApplicationEntity -> {
          JobApplication jobApplication = new JobApplication(
              jobApplicationEntity.getId(),
              jobApplicationEntity.getTitle(),
              jobApplicationEntity.getDescription(),
              jobApplicationEntity.getTags());
          jobApplications.add(jobApplication);
        });
    return jobApplications;
  }
}
