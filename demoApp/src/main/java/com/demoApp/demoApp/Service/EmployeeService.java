package com.demoApp.demoApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demoApp.demoApp.DAO.EmployeeDAOImpl;
import com.demoApp.demoApp.Utils.ServiceException;
import com.demoApp.demoApp.model.Employee;

@Service
public class EmployeeService {
	
	 	@Autowired
	    private EmployeeDAOImpl employeeDAO;

	    public List<Employee> get() {
	        List<Employee> employees = employeeDAO.findAll();
	        if (employees.isEmpty()) {
	            throw new ServiceException(HttpStatus.NOT_FOUND, "No employees found");
	        }
	        return employees;
	    }

	    public Employee getById(Long id) {
	        if (id <= 0) {
	            throw new ServiceException(HttpStatus.BAD_REQUEST, "Invalid Employee Id: " + id);
	        }

	        return employeeDAO.findById(id)
	                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Employee with ID " + id + " not found"));
	    }

	    public Employee add(Employee employee) {
	        if (employee == null) {
	            throw new ServiceException(HttpStatus.BAD_REQUEST, "Employee data cannot be null");
	        }

	        return employeeDAO.save(employee)
	                .orElseThrow(() -> new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving employee"));
	    }

	    public Employee update(Employee employee) {
	        if (employee == null || employee.getId() == null || employee.getId() <= 0) {
	            throw new ServiceException(HttpStatus.BAD_REQUEST, "Invalid Employee ID");
	        }
	        

	        return employeeDAO.update(employee)
	                .orElseThrow(() -> new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating employee"));
	    }

	    public void deleteById(Long id) {
	        if (id <= 0) {
	            throw new ServiceException(HttpStatus.BAD_REQUEST, "Invalid Employee Id: " + id);
	        }

	        boolean isDeleted = employeeDAO.deleteById(id);
	        if (!isDeleted) {
	            throw new ServiceException(HttpStatus.NOT_FOUND, "Employee with ID " + id + " not found");
	        }
	    }

}
