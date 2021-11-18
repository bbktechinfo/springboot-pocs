package com.example.api.model;

public class EmployeeResponse {
	
	private long employeeId;
	private String employeeName;
	private String department;
	private double salary;
	
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "EmployeeResponse [employeeId=" + employeeId + ", employeeName=" + employeeName + ", department="
				+ department + ", salary=" + salary + "]";
	}
}
