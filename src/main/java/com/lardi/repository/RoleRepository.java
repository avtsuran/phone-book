package com.lardi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lardi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);
}
