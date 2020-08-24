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
}
