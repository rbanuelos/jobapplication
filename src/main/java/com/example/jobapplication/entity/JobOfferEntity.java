package com.example.jobapplication.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DB Entity that represents a Job Offer.
 */
@Entity
@Table(name = "job_offer")
public class JobOfferEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column
  private Long id;

  @Column
  private String title;

  @Column
  private String description;

  @Column
  private String tags;

  @OneToMany(mappedBy = "jobOffer", fetch = FetchType.LAZY)
  private List<JobApplicationEntity> jobApplications;

  @Column
  private boolean closed;

  /**
   * Class constructor.
   *
   * @param title job offer title
   * @param description job offer description
   * @param tags comma separated list of tags associated to job offer
   */
  public JobOfferEntity(String title, String description, String tags) {
    this.title = title;
    this.description = description;
    this.tags = tags;
  }

  public JobOfferEntity() {

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

  public boolean isClosed() {
    return closed;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public List<JobApplicationEntity> getJobApplications() {
    return jobApplications;
  }

  @Override
  public String toString() {
    return "JobOfferEntity{"
        + "id='" + id.toString() + '\''
        + ", title='" + title + '\''
        + ", description='" + description + '\''
        + ", tags=" + tags
        + '}';
  }
}
