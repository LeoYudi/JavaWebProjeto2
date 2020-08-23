package com.example.spring.models;

import javax.persistence.*;

@Entity
public class Favorite {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private long movie_id;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;
}
