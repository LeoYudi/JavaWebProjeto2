package com.example.spring.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Likes implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @Column(nullable = false)
  private long movieId;
  
  @Column
  private int value; // -1 = deslike, 0 = neutro, 1 = like
  
  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;
  
  public static long getSerialVersionUID() {
    return serialVersionUID;
  }
  
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public long getMovieId() {
    return movieId;
  }
  
  public void setMovieId(long movieId) {
    this.movieId = movieId;
  }
  
  public int getValue() {
    return value;
  }
  
  public void setValue(int value) {
    this.value = value;
  }
  
  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }
}
