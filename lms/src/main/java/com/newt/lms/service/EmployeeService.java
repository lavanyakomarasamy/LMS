package com.newt.lms.service;

import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.newt.lms.constants.StatusCode;
import com.newt.lms.exception.ApplicationException;
import com.newt.lms.exception.DataNotFoundException;
import com.newt.lms.jpa.repository.EmployeeRepository;
import com.newt.lms.mapper.ResourceMapper;
import com.newt.lms.model.jpa.dao.Employee;
import com.newt.lms.model.jpa.dto.EmployeeDTO;

/**
 * @author nareshd
 *
 */
@Service
public class EmployeeService {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(EmployeeService.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ResourceMapper resourceMapper;

	public Employee getEmployee(Employee employeeReq) {
		return employeeRepository.findOne(employeeReq.getEmployeeId());
	}

	/**
	 * Create Employee
	 * 
	 * @param employeeDTO
	 * @return
	 * @throws ApplicationException 
	 */
	public Employee createEmployee(EmployeeDTO employeeDTO) throws ApplicationException {
		Date date = new Date();
		
		Employee emp = employeeRepository.findByEmployeeId(employeeDTO.getEmployeeId());
		if (emp == null) {
			Employee employee = resourceMapper.convertEmployeeDTOToEmployeeDAO(employeeDTO);
			employee.setCreatedDate(date);
			employee.setModifiedDate(date);
			employee = employeeRepository.save(employee);
			return employee;
		}
		else
		{
			LOGGER.warn("Record already existed for the given employeeId - " + employeeDTO.getEmployeeId());
			throw new ApplicationException(StatusCode.NOT_CREATED.getCode(), StatusCode.NOT_CREATED.getDesc());
		}
	}

	/**
	 * Update Employee
	 * 
	 * @param employeeDTO
	 * @return
	 * @throws DataNotFoundException
	 */
	public Employee updateEmployee(EmployeeDTO employeeDTO) throws DataNotFoundException {

		Date date = new Date();
		Employee empDao = employeeRepository.findByEmployeeId(employeeDTO.getEmployeeId());

		if (empDao == null) {
			LOGGER.warn("Record doesn't exist for the given employeeId - " + employeeDTO.getEmployeeId());
			throw new DataNotFoundException(StatusCode.DATA_NOT_FOUND.getCode(), StatusCode.DATA_NOT_FOUND.getDesc());
		}

		String designation = employeeDTO.getDesignation();
		Long directReporterId = employeeDTO.getDirectReporterId();
		Date dob = employeeDTO.getDob();
		Date doj = employeeDTO.getDoj();
		String employeeName = employeeDTO.getEmployeeName();
		String maritalStatus = employeeDTO.getMaritalStatus();

		if (designation != null) {
			empDao.setDesignation(designation);
		}
		if (directReporterId != null) {
			empDao.setDirectReporterId(directReporterId);
		}
		if (dob != null) {
			empDao.setDob(dob);
		}
		if (doj != null) {
			empDao.setDoj(doj);
		}
		if (employeeName != null) {
			empDao.setEmployeeName(employeeName);
		}
		if (maritalStatus != null) {
			empDao.setMaritalStatus(maritalStatus);
		}
		empDao.setModifiedDate(date);
		return employeeRepository.save(empDao);
	}
	
	/**
	 * Get Employee
	 * 
	 * @param employeeDTO
	 * @return
	 * @throws DataNotFoundException
	 */
	public Employee getEmployee(EmployeeDTO employeeDTO) throws DataNotFoundException {

		Employee empDao = employeeRepository.findByEmployeeId(employeeDTO.getEmployeeId());

		if (empDao == null) {
			LOGGER.warn("Record doesn't exist for the given employeeId - " + employeeDTO.getEmployeeId());
			throw new DataNotFoundException(StatusCode.DATA_NOT_FOUND.getCode(), StatusCode.DATA_NOT_FOUND.getDesc());
		} else {
			return empDao;
		}
	}
	
	/**
	 * Delete Employee
	 * 
	 * @param employeeId
	 * @return
	 * @throws DataNotFoundException
	 */
	public boolean deleteEmployee(Long employeeId) throws DataNotFoundException {

		boolean isDeleted = false;
		Employee employeeDao = employeeRepository.findByEmployeeId(employeeId);
		if (employeeDao != null) {
			employeeRepository.delete(employeeId);
			return isDeleted = true;
		}

		return false;
	}

}
