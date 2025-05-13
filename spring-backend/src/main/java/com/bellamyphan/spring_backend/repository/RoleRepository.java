package com.bellamyphan.spring_backend.repository;

import com.bellamyphan.spring_backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // JpaRepository provides basic CRUD operations
}
