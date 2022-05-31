package com.example.jobapplication.entity;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("JobApplication")
public class JobApplicationEntity implements Serializable {
  private String id;
  private String title;
  private String description;
  private List<String> tags;

  public JobApplicationEntity(String id, String title, String description, List<String> tags) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.tags = tags;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getTags() {
    return tags;
  }
}
