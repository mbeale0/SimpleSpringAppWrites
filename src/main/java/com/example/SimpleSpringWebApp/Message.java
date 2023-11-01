package com.example.SimpleSpringWebApp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Message {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String userMessage;

  private String title;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserMessage() {
    return userMessage;
  }

  public void setuserMessage(String userMessage) {
    this.userMessage = userMessage;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}