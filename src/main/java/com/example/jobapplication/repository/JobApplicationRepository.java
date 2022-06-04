package com.example.jobapplication.repository;

import com.example.jobapplication.entity.JobApplicationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Job Application Entity Repository class.
 */
@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplicationEntity, Long> {
  List<JobApplicationEntity> findByJobOfferId(Long jobOfferId);
}
