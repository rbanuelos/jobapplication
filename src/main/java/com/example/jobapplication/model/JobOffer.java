package com.example.jobapplication.model;

import java.io.Serializable;

/**
 * Class that represents a Job Offer.
 */
public class JobOffer implements Serializable {

  private Long id;
  private String title;
  private String description;
  private String tags;

  private boolean closed;
  private Integer openApplications;

  /**
   * Class constructor.
   *
   * @param id               object identifier
   * @param title            job offer title
   * @param description      job offer description
   * @param tags             comma separated values for tags associated with job offer
   * @param openApplications number of job applications
   * @param closed           if offer is no longer available
   */
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
