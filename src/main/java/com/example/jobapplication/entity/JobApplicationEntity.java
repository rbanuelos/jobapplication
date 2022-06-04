package com.example.jobapplication.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DB Entity that represents a Job Application.
 */
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

  @ManyToOne
  @JoinColumn(name = "job_offer_id")
  private JobOfferEntity jobOffer;

  /**
   * Class constructor.
   *
   * @param fullName applicant's fullname
   * @param address  applicant's address
   * @param phone    applicant's phone
   */
  public JobApplicationEntity(String fullName, String address, String phone) {
    this.fullName = fullName;
    this.address = address;
    this.phone = phone;
  }

  public JobApplicationEntity() {

  }

  public void setJobOffer(JobOfferEntity jobOffer) {
    this.jobOffer = jobOffer;
  }

  public Long getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }
}
