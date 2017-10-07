package com.newt.lms.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import com.newt.lms.model.jpa.dao.Employee;
import com.newt.lms.model.jpa.dto.EmployeeDTO;

import ma.glasnost.orika.MapperFacade;

/**
 * @author nareshd
 *
 */
public class ResourceMapper {

	@Autowired
	MapperFacade mapper;

	public Employee convertEmployeeDTOToEmployeeDAO(EmployeeDTO employeeDTO) {
		return mapper.map(employeeDTO, Employee.class);
	}

}
