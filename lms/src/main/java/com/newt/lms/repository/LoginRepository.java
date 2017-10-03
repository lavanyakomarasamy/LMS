package com.newt.lms.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newt.lms.model.Login;

/**
 * @author lavanyak
 *
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
	
	List<Login> findAll(Specification<Login> login);
}
