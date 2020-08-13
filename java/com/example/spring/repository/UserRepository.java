package com.example.spring.repository;

import com.example.spring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  public List<User> findByEmail(String email);
}
