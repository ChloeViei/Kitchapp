package com.chloeviei.spring.auth.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chloeviei.spring.auth.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findById(Long id);
  
  Boolean existsByEmail(String email);
}