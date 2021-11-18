package com.example.api.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.example.api.model.EmployeeEntity;
import com.example.api.model.EmployeeResponse;
import com.example.api.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/employeemanagement")
@Api(value = "employee management", description = "Operations pertaining to employees in organization")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@ApiOperation(value = "View a list of available products", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 405, message = "The resource you were trying to reach whose HTTP Method Not Allowed") })
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
		logger.info("EmployeeController----getAllEmployees() method invocation started. ");
		List<EmployeeResponse> employees = employeeService.findAllEmployees();
		logger.info("EmployeeController----employeeService findAllEmployees() returned employees size : "
				+ employees.size());
		return new ResponseEntity<List<EmployeeResponse>>(employees, HttpStatus.OK);
	}

	@ApiOperation(value = "Search an employee with an ID", response = EmployeeResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 405, message = "The resource you were trying to reach whose HTTP Method Not Allowed"),
			@ApiResponse(code = 400, message = "Bad Request") })
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {

		logger.info("EmployeeController----getEmployeeById() method invocation started. ");
		EmployeeResponse employeeResponse = employeeService.findEmpById(id);

		return new ResponseEntity<EmployeeResponse>(employeeResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Adds a new Employee")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created the resouce"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 405, message = "The resource you were trying to reach whose HTTP Method Not Allowed"),
			@ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping("/employees")
	public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeEntity emp) {
		EmployeeResponse employee = employeeService.save(emp);
		return new ResponseEntity<EmployeeResponse>(employee, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Updates an existing Employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated the resouce. "),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found. "),
			@ApiResponse(code = 405, message = "The resource you were trying to reach whose HTTP Method Not Allowed. "),
			@ApiResponse(code = 400, message = "The resource you were trying to reach whose input is bad format. ") })
	@PutMapping("/employees/{empId}")
	public ResponseEntity<EmployeeEntity> updateEmpById(@PathVariable("empId") Long empId, @RequestBody EmployeeEntity emp) {
		EmployeeEntity updatedEmpResponse = employeeService.updateEmpById(empId, emp);

		return new ResponseEntity<EmployeeEntity>(updatedEmpResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "Deletes an existing Employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted the resouce. "),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found. "),
			@ApiResponse(code = 405, message = "The resource you were trying to reach whose HTTP Method Not Allowed. "),
			@ApiResponse(code = 400, message = "The resource you were trying to reach whose input is bad format. ") })
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmpById(@PathVariable Long id) {
		logger.info("EmployeeController - deleteEmpById(id) :" + id);
		employeeService.delete(id);
		return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
	}
}