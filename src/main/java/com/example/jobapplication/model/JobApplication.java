package com.example.jobapplication.model;

import java.io.Serializable;

/**
 * Class that represent a job application.
 */
public class JobApplication implements Serializable {

  private static final long serialVersionUID = 22222L;

  private final String fullName;
  private final String address;
  private final String phone;

  /**
   * Class constructor.
   *
   * @param fullName applicant's full name
   * @param address  applicant's address
   * @param phone    applicant's phone
   */
  public JobApplication(String fullName, String address, String phone) {
    this.fullName = fullName;
    this.address = address;
    this.phone = phone;
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
