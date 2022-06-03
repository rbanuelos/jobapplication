package com.example.jobapplication.model;

public class JobOffer {

  private Long id;
  private String title;
  private String description;
  private String tags;
  private Integer openApplications;

  public JobOffer(Long id, String title, String description, String tags,
                  Integer openApplications) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.tags = tags;
    this.openApplications = openApplications;
  }
}
