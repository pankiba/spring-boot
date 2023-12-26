package com.pankiba.springcache.service;

import java.util.List;

import com.pankiba.springcache.domain.Employee;

public interface EmployeeService {

	// Basic CRUD operations

	public Employee saveEmployee(Employee employee);

	public List<Employee> saveEmployees(List<Employee> employees);

	public Employee findEmployee(Long employeeId);

	public List<Employee> findEmployees();

	public Employee updateEmployee(Employee employee);

	public void deleteEmployee(Long employeeId);

	public void deleteAllEmployees();


}
