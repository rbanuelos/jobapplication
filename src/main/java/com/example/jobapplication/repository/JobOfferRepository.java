package com.example.jobapplication.repository;

import com.example.jobapplication.entity.JobOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOfferEntity, Long> {}