package com.example.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.exception.ResourceNotFoundException;
import com.example.api.model.EmployeeEntity;
import com.example.api.model.EmployeeResponse;
import com.example.api.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeResponse> findAllEmployees() {
		List<EmployeeEntity> employees = employeeRepository.findAll();

		List<EmployeeResponse> empResList = new ArrayList<>();
		for (EmployeeEntity emp : employees) {

			EmployeeResponse updatedResponse = new EmployeeResponse();
			updatedResponse.setEmployeeId(emp.getEmployeeId());
			updatedResponse.setDepartment(emp.getDepartment());
			updatedResponse.setEmployeeName(emp.getEmployeeName());
			updatedResponse.setSalary(emp.getSalary());

			empResList.add(updatedResponse);
		}

		return empResList;
	}

	public EmployeeResponse findEmpById(Long employeeId) {
		Optional<EmployeeEntity> empResFromDB = employeeRepository.findById(employeeId);
		if (empResFromDB.isPresent()) {
			
			EmployeeResponse updatedResponse = new EmployeeResponse();
			updatedResponse.setEmployeeId(empResFromDB.get().getEmployeeId());
			updatedResponse.setDepartment(empResFromDB.get().getDepartment());
			updatedResponse.setEmployeeName(empResFromDB.get().getEmployeeName());
			updatedResponse.setSalary(empResFromDB.get().getSalary());

			return updatedResponse;
		} else {
			throw new ResourceNotFoundException("Invalid Employee ID " + employeeId);
		}

	}

	public EmployeeResponse save(EmployeeEntity employee) {
		EmployeeEntity updatedEmployee = employeeRepository.save(employee);
		EmployeeResponse updatedResponse = new EmployeeResponse();
		updatedResponse.setEmployeeId(updatedEmployee.getEmployeeId());
		updatedResponse.setDepartment(updatedEmployee.getDepartment());
		updatedResponse.setEmployeeName(updatedEmployee.getEmployeeName());
		updatedResponse.setSalary(updatedEmployee.getSalary());

		return updatedResponse;
	}

	public EmployeeEntity updateEmpById(Long empId, EmployeeEntity emp) {
		
		EmployeeEntity empResponseFromDB = employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Post"));

		logger.info("EmployeeService ----- updateEmpById() method empResponseFromDB : "+empResponseFromDB);

		empResponseFromDB.setEmployeeName(emp.getEmployeeName());
		empResponseFromDB.setDepartment(emp.getDepartment());
		empResponseFromDB.setSalary(emp.getSalary());

		EmployeeEntity updatedEmpResponse = employeeRepository.save(empResponseFromDB);
		return updatedEmpResponse;
	}

	public void delete(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}
}
