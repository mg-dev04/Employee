package com.employee.manage.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String dob;
    private String role;
    private String dept;
    private String mobile;
    private String joiningDate;
    private String email;
    private String gender;
    private String address;
    private String status;
}
