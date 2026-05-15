package com.employee.manage.Services;

import com.employee.manage.Model.Employee;
import com.employee.manage.Repo.employeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class employeeService {

    @Autowired
    private employeeRepo empRepo;

    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return empRepo.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return empRepo.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updated) {
        Optional<Employee> existing = empRepo.findById(id);
        if (existing.isEmpty()) return null;

        Employee emp = existing.get();
        emp.setName(updated.getName());
        emp.setDob(updated.getDob());
        emp.setRole(updated.getRole());
        emp.setDept(updated.getDept());
        emp.setMobile(updated.getMobile());
        emp.setJoiningDate(updated.getJoiningDate());
        emp.setEmail(updated.getEmail());
        emp.setGender(updated.getGender());
        emp.setAddress(updated.getAddress());
        emp.setStatus(updated.getStatus());

        return empRepo.save(emp);
    }

    public Boolean deleteEmployee(Long id) {
        if (empRepo.existsById(id)) {
            empRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Employee> searchByName(String name) {
        return empRepo.findByNameContainingIgnoreCase(name);
    }
}
