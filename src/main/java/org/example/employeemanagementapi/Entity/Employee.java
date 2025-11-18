package org.example.employeemanagementapi.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data   // gives getters and setters automatically
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String position;

    private Double salary;
}
