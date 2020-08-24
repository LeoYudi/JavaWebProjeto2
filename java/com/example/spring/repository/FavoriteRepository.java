package com.example.spring.repository;


import com.example.spring.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository  extends JpaRepository<Favorite, Long> {
  public List<Favorite> findByMovieId(long id);
}
