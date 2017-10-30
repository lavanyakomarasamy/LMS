/**
 * 
 */
package com.newt.lms.jpa.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.newt.lms.model.jpa.dao.Employee;

/**
 * @author nareshd
 *
 */
public class EmployeeSpec implements Specification<Employee> {

	private static final long serialVersionUID = 1L;

	final Employee employee;

	public EmployeeSpec(Employee employee) {
		this.employee = employee;
	}

	@Override
	public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		List<Predicate> criteria = new ArrayList<Predicate>();

		if (employee != null) {
			if (employee.getEmployeeId() != null) {
				criteria.add(cb.equal(root.get("employeeId"), employee.getEmployeeId()));
			}

			if (employee.getDesignation() != null) {
				criteria.add(cb.equal(root.get("designation"), employee.getDesignation()));
			}

			if (employee.getDirectReporterId() != null) {
				criteria.add(cb.equal(root.get("directReporterId"), employee.getDirectReporterId()));
			}
			if (employee.getDob() != null) {
				criteria.add(cb.equal(root.get("dob"), employee.getDob()));
			}
			if (employee.getDoj() != null) {
				criteria.add(cb.equal(root.get("doj"), employee.getDoj()));
			}
			if (employee.getEmployeeName() != null) {
				criteria.add(cb.equal(root.get("employeeName"), employee.getEmployeeName()));
			}
			if (employee.getMaritalStatus() != null) {
				criteria.add(cb.equal(root.get("maritalStatus"), employee.getMaritalStatus()));
			}
		}

		return null;
	}

}
