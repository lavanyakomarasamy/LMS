package com.newt.lms.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newt.lms.model.jpa.dao.Employee;

/**
 * @author lavanyak
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Employee findAll(Specification<Employee> employee);
}
