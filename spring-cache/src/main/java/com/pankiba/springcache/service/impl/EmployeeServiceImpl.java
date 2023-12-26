package com.pankiba.springcache.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pankiba.springcache.domain.Employee;
import com.pankiba.springcache.repository.EmployeeRepository;
import com.pankiba.springcache.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> saveEmployees(List<Employee> employees) {
		return employeeRepository.saveAll(employees);
	}

	@Override
	@Cacheable(value = "employee", key = "#employeeId")
	public Employee findEmployee(Long employeeId) {
		simulateDelay();
		return employeeRepository.findById(employeeId).get();
	}

	@Override
	@Cacheable(value = "employees")
	public List<Employee> findEmployees() {
		simulateDelay();
		return employeeRepository.findAll();
	}

	@Override
	@CachePut(value = "employee", key = "#employee.employeeId")
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	@CacheEvict(value = "employee", key = "#employee.employeeId")
	public void deleteEmployee(Long employeeId) {
		employeeRepository.delete(employeeRepository.findById(employeeId).get());
	}

	@Override
	@CacheEvict(value="employees", allEntries=true)
	public void deleteAllEmployees() {
		employeeRepository.deleteAll();
	}

	private void simulateDelay() {
		log.info("simulating delay");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
