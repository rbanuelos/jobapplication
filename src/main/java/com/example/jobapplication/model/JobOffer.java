package com.example.jobapplication.model;

import java.util.List;

public class JobOffer {
  private String id;
  private String title;
  private String description;
  private List<String> tags;

  public JobOffer(String id, String title, String description, List<String> tags) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.tags = tags;
  }
}
