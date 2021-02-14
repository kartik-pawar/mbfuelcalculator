package com.mbfuelcalculator.app.repo.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbfuelcalculator.app.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
