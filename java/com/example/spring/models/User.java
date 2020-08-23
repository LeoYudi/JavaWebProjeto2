package com.example.spring.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private String name;

  @Column
  private String email;

  @Column
  private String password;

  @Column
  private String image;

  @OneToMany(mappedBy = "user")
  private List<Comment> comments = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<Favorite> favorites = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<Likes> likes = new ArrayList<>();

  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getImage() {
    return image;
  }
  
  public void setImage(String image) {
    this.image = image;
  }

}
