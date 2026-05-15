package com.employee.manage.Controller;

import com.employee.manage.Model.Employee;
import com.employee.manage.Services.employeeService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class employeeController {

    @Autowired
    private employeeService empService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(empService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        Optional<Employee> emp = empService.getEmployeeById(id);
        if (emp.isEmpty()) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(emp.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee created = empService.addEmployee(employee);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updated = empService.updateEmployee(id, employee);
        if (updated == null) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        Boolean deleted = empService.deleteEmployee(id);
        if (!deleted) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam String name) {
        return new ResponseEntity<>(empService.searchByName(name), HttpStatus.OK);
    }
}
