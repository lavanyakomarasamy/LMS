package com.newt.lms.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newt.lms.constants.EmployeeConstants;
import com.newt.lms.constants.StatusCode;
import com.newt.lms.exception.ApplicationException;
import com.newt.lms.model.jpa.dao.Employee;
import com.newt.lms.model.jpa.dao.EmployeeResponse;
import com.newt.lms.model.jpa.dao.ResponseStatus;
import com.newt.lms.model.jpa.dto.EmployeeDTO;
import com.newt.lms.service.EmployeeService;
import com.newt.lms.utils.EmployeeUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/LMS")
public class EmployeeController {
	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	
	
	/*@Autowired
	EmployeeUtils employeeUtils;
*/
	/**
	 * Create Employee
	 * 
	 * @param EmployeeDTO
	 * @return
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = { "*" })
	@ApiOperation(value = "Create Employee", notes = "Returns Response Of Employee Creation. SLA:500", response = EmployeeResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Created Employee", response = EmployeeResponse.class),
			@ApiResponse(code = 404, message = "Creation Of Employee Failed"),
			@ApiResponse(code = 400, message = "Invalid Input Provided") })
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeDTO employeeDTO) throws ApplicationException {

		EmployeeResponse response = new EmployeeResponse();
		Boolean returnCreateStatus = false;
		String createStatusCode = null;
		String createstatusMsg = null;

		validateCreateEmployee(employeeDTO);
		Employee employee = employeeService.createEmployee(employeeDTO);
		if (employee != null) {
			returnCreateStatus = true;
			createStatusCode = StatusCode.SUCCESS.getCode();
			createstatusMsg = StatusCode.SUCCESS.getDesc();

		} else {
			createStatusCode = StatusCode.DATA_NOT_FOUND.getCode();
			createstatusMsg = StatusCode.DATA_NOT_FOUND.getDesc();
		}

		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setCode(createStatusCode);
		responseStatus.setDescription(createstatusMsg);
		response.setStatus(responseStatus);

		if (returnCreateStatus) {
			response.setEmployee(employee);
			LOGGER.debug("Employee created successfully");
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}

	private void validateCreateEmployee(EmployeeDTO employeeDTO) throws ApplicationException {
		String validateStatus = valCrteEmpMandFildsChck(employeeDTO);
		if (validateStatus != null) {
			throw new ApplicationException(StatusCode.BAD_REQUEST.getCode(), validateStatus);
		}
	}
	
	public String valCrteEmpMandFildsChck(EmployeeDTO employeeDTO) {
		
		Long empId = employeeDTO.getEmployeeId() == null ? 0L : employeeDTO.getEmployeeId();
		String empName = employeeDTO.getEmployeeName() == null ? "" : employeeDTO.getEmployeeName().trim();
		Date doj = employeeDTO.getDoj();
		Date dob = employeeDTO.getDob();

		if (empId == 0) {
			return EmployeeConstants.EMP_ID;
		}
		if (empName == null || empName.isEmpty()) {
			return EmployeeConstants.EMP_NAME;
		}
		if (doj == null) {
			return EmployeeConstants.DOJ;
		}
		if (dob == null) {
			return EmployeeConstants.DOB;
		}
		return null;
	}

}
