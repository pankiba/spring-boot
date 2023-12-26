package com.pankiba.springcache.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pankiba.springcache.domain.Employee;
import com.pankiba.springcache.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SpringCacheShowCaseResource {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employees")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {

		log.info("Save - Single Entity");
		Employee savedEmployee = employeeService.saveEmployee(employee);

		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
	}

	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> findEmployee(@PathVariable Long employeeId) {

		log.info("Read - Single Entity");
		Employee employee = employeeService.findEmployee(employeeId);

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> findAllEmployees() {

		log.info("Read - All Entities");
		List<Employee> employeeList = employeeService.findEmployees();

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}
	
	
	@PutMapping("/employees")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {

		log.info("Update - Single Entity");
		Employee savedEmployee = employeeService.updateEmployee(employee);

		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
	}

	@DeleteMapping("/employees/{employeeId}")
	public void deleteEmployee(@PathVariable Long employeeId) {

		log.info("Delete - Single Entity");
		employeeService.deleteEmployee(employeeId);
	}

}
