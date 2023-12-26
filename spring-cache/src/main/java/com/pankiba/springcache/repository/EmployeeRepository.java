package com.pankiba.springcache.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.springcache.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
