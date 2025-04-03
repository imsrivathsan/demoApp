package com.demoApp.demoApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoApp.demoApp.Service.EmployeeService;
import com.demoApp.demoApp.model.Employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/backendapp/api/employee")
@Tag(name = "Employee API", description = "Endpoints for managing employees")
public class EmployeeController {
	
	@Autowired
    private EmployeeService service;

    @GetMapping("/get/all")
    @Operation(summary = "Get all employees", description = "Fetch all employees from the database")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(service.get());
    }

    
    @GetMapping("/get/employee/by/id/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    
    @PostMapping("/add/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(employee));
    }

    
    @PutMapping("/update/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(service.update(employee));
    }

    @DeleteMapping("/delete/by/id/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build(); 
    }

}
