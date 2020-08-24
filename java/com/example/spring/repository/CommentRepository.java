package com.example.spring.repository;

import com.example.spring.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  public List<Comment> findByMovieId(long id);
}
