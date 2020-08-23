package com.example.spring.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Favorite implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private long movie_id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
