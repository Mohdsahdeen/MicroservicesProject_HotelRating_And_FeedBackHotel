package com.userservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.userservices.entities.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, String>{

}
