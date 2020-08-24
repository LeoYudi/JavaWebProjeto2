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
  private long movieId;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;
}
