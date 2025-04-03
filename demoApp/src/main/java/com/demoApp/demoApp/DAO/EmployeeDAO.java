package com.demoApp.demoApp.DAO;

import java.util.List;
import java.util.Optional;

import com.demoApp.demoApp.model.Employee;

public interface EmployeeDAO {
	
	Optional<Employee> save(Employee employee);  

    Optional<Employee> update(Employee employee); 

    boolean deleteById(Long id); 

    Optional<Employee> findById(Long id); 

    List<Employee> findAll(); 

    
}
