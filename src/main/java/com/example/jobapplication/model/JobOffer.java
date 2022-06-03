package com.example.jobapplication.model;

public class JobOffer {

  private Long id;
  private String title;
  private String description;
  private String tags;

  private boolean closed;
  private Integer openApplications;

  public JobOffer(Long id, String title, String description, String tags,
                  Integer openApplications, boolean closed) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.tags = tags;
    this.closed = closed;
    this.openApplications = openApplications;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getTags() {
    return tags;
  }

  public Integer getOpenApplications() {
    return openApplications;
  }

  public boolean isClosed() {
    return closed;
  }
}
