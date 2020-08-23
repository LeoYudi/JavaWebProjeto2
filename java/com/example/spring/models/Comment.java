package com.example.spring.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Comment implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private long movie_id;

  @Column(length = 250)
  private String comment;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;
}
