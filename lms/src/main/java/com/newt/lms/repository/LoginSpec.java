package com.newt.lms.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.newt.lms.model.jpa.dao.Login;


public class LoginSpec implements Specification<Login> {
	
	final Login login;
	
	public LoginSpec(Login login) {
		this.login = login;
	}

	@Override
	public Predicate toPredicate(Root<Login> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> criteria = new ArrayList<Predicate>();
		if (login != null) {

			if (login.getUserId() != null && !login.getUserId().isEmpty()) {
				criteria.add(cb.equal(root.get("userId"), login.getUserId()));
			}

			if (login.getPassword() != null && !login.getPassword().isEmpty()) {
				criteria.add(cb.equal(root.get("password"), login.getPassword()));
			}
			
			if (login.getEmployee() != null && login.getEmployee().getEmployeeId() != 0) {
				criteria.add(cb.equal(root.get("employeeId"), login.getEmployee().getEmployeeId()));
			}

			
		}
		Predicate pred = null;

		if (criteria.size() == 1) {
			pred = criteria.get(0);
		} else if (criteria.size() > 1) {
			pred = cb.and(criteria.toArray(new Predicate[0]));
		}
		return pred;
	}

}
