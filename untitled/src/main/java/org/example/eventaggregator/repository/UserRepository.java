package org.example.eventaggregator.repository;

import org.example.eventaggregator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {}
