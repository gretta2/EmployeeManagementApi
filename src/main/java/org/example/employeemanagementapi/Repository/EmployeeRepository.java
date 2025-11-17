package org.example.employeemanagementapi.Repository;

import org.example.employeemanagementapi.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
