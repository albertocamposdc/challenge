package com.example.clip.repository;

import com.example.clip.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u  FROM User u where user_id=?1")
	User findById(String user_id);
}
