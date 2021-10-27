package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.Employee;
import com.example.api.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findAllEmployees(){
		return employeeRepository.findAll();		
	}
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Optional<Employee> get(int employeeId) {
		return employeeRepository.findById(employeeId);
	}
	
	public void delete(int employeeId) {
		employeeRepository.deleteById(employeeId);
	}
}
