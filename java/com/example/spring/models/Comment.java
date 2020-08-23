package com.example.spring.models;


import javax.persistence.*;

@Entity
public class Comment{

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
