package com.employee.manage.Repo;

import com.employee.manage.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface employeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByDept(String dept);
    List<Employee> findByStatus(String status);
    List<Employee> findByNameContainingIgnoreCase(String name);
}
