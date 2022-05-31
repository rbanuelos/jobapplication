package com.example.jobapplication.repository;

import com.example.jobapplication.entity.JobApplicationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends CrudRepository<JobApplicationEntity, String> {}