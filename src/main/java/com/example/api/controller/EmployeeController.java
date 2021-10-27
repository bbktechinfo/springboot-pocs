package com.example.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Employee;
import com.example.api.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
		
		Optional<Employee> empResponse = employeeService.get(id);
		
		if(empResponse.isPresent()) {
	        return ResponseEntity.ok().body(empResponse.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		
		List<Employee> employees = employeeService.findAllEmployees();
		
		if(employees.isEmpty()) {
			 return ResponseEntity.notFound().build();
	    } else {
	    	 return ResponseEntity.ok().body(employees);
	    }
	}

	@PostMapping("/employee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp) {
		Employee employee = employeeService.save(emp);

		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}
}
