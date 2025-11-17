package org.example.employeemanagementapi.Repository;

import org.example.employeemanagementapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByEmail(String email);
}
