package com.example.jobapplication.model;

public class JobApplication {
  private Long id;
  private String fullName;
  private String address;
  private String phone;

  public JobApplication(Long id, String fullName, String address, String phone) {
    this.id = id;
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
