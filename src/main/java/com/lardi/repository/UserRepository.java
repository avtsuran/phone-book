package com.lardi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lardi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
}
