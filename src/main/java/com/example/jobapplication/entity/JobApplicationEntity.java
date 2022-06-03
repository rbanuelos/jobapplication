package com.example.jobapplication.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "job_application")
public class JobApplicationEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column
  private Long id;

  @Column
  private String fullName;

  @Column
  private String address;

  @Column
  private String phone;
}
