package com.demoApp.demoApp.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demoApp.demoApp.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
	
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Optional<Employee> save(Employee employee) {
		
		 String sql = "INSERT INTO employee (id, emp_id, designation) VALUES (:id, :empId, :designation)";
	        
	        int rowsAffected = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(employee));
	        
	        return rowsAffected > 0 ? Optional.of(employee) : Optional.empty();
	}

	@Override
	public Optional<Employee> update(Employee employee) {

		String sql = "UPDATE employee SET emp_id = :empId, designation = :designation WHERE id = :id";

		int rowsAffected = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(employee));

		return rowsAffected > 0 ? Optional.of(employee) : Optional.empty();
	}

	@Override
	public boolean deleteById(Long id) {
		
		
		String sql = "DELETE FROM employee WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        
        int rowsAffected = jdbcTemplate.update(sql, params);
        
        return rowsAffected > 0;
	}

	@Override
	public Optional<Employee> findById(Long id) {

		String sql = "SELECT * FROM employee WHERE id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);

		try {
			Employee employee = jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Employee.class));
			return Optional.ofNullable(employee);
		} catch (Exception e) {
			return Optional.empty();
		}

	}

	@Override
	public List<Employee> findAll() {

		String sql = "SELECT * FROM employee";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
	}
	
	
	
	

}
