package com.newt.lms.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.newt.lms.model.jpa.dao.Employee;


/**
 * @author lavanyak
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findAll(Specification<Employee> employee);
	
	Employee findByEmployeeId(Long employee);
	
}
