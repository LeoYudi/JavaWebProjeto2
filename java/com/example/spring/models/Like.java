package com.example.spring.models;


import javax.persistence.*;

@Entity
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private long movie_id;

  @Column(length = 250)
  private short value; // -1 = deslike, 0 = neutro, 1 = like

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;
}
