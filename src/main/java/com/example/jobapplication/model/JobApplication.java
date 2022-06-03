package com.example.jobapplication.model;

public class JobApplication {

  private String id;
  private String jobOfferId;
  private Status status;
  private String applicantFullName;
  private String applicantAddress;
  private String applicantPhone;
  private String applicantWorkExperience;

  public enum Status {
    PENDING, APPROVED, REJECTED
  }
}
