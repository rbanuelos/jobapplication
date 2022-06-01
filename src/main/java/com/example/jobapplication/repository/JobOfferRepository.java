package com.example.jobapplication.repository;

import com.example.jobapplication.entity.JobOfferEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends CrudRepository<JobOfferEntity, String> {}