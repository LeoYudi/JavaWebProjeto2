package com.example.spring.repository;

import com.example.spring.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {
  public List<Likes> findByMovieId(long id);
}
