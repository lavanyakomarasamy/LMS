package com.newt.lms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newt.lms.constants.StatusCode;
import com.newt.lms.exception.ApplicationException;
import com.newt.lms.exception.DataNotFoundException;
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

	@Autowired
	EmployeeUtils employeeUtils;

	/**
	 * Create Employee
	 * 
	 * @param EmployeeDTO
	 * @return
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Create Employee", notes = "Returns Response Of Employee Creation. SLA:500", response = EmployeeResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Created Employee", response = EmployeeResponse.class),
			@ApiResponse(code = 404, message = "Creation Of Employee Failed"),
			@ApiResponse(code = 400, message = "Invalid Input Provided") })
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeDTO employeeDTO)throws ApplicationException {

		EmployeeResponse response = new EmployeeResponse();
		Boolean returnCreateStatus = false;
		String createStatusCode = null;
		String createstatusMsg = null;

		validateEmployee(employeeDTO, "Create");
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

	/**
	 * Update Employee
	 * 
	 * @param EmployeeDTO
	 * @return
	 * @throws ApplicationException
	 * @throws DataNotFoundException
	 */
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Update Employee", notes = "Returns Response Of Employee Creation. SLA:500", response = EmployeeResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Created Employee", response = EmployeeResponse.class),
			@ApiResponse(code = 404, message = "Creation Of Employee Failed"),
			@ApiResponse(code = 400, message = "Invalid Input Provided") })
	public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeDTO employeeDTO)throws ApplicationException, DataNotFoundException {

		EmployeeResponse response = new EmployeeResponse();
		Boolean returnCreateStatus = false;
		String createStatusCode = null;
		String createstatusMsg = null;

		validateEmployee(employeeDTO, "Update");
		Employee employee = employeeService.updateEmployee(employeeDTO);
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
			LOGGER.debug("Employee updated successfully");
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}

	/**
	 * Get Employee
	 * 
	 * @param EmployeeDTO
	 * @return
	 * @throws ApplicationException
	 * @throws DataNotFoundException
	 */
	@RequestMapping(value = "/getEmployee", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Get Employee", notes = "Returns Response Of Employee. SLA:500", response = EmployeeResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Created Employee", response = EmployeeResponse.class),
			@ApiResponse(code = 404, message = "Creation Of Employee Failed"),
			@ApiResponse(code = 400, message = "Invalid Input Provided") })
	public ResponseEntity<EmployeeResponse> getEmployee(@RequestBody EmployeeDTO employeeDTO)throws ApplicationException, DataNotFoundException {

		EmployeeResponse response = new EmployeeResponse();
		Boolean returnCreateStatus = false;
		String createStatusCode = null;
		String createstatusMsg = null;

		validateEmployee(employeeDTO, "Get");
		Employee employee = employeeService.getEmployee(employeeDTO);
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
			LOGGER.debug("Employee details retrived successfully");
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}
	/**
	 * Get Employee
	 * 
	 * @param EmployeeDTO
	 * @return
	 * @throws ApplicationException
	 * @throws DataNotFoundException
	 */
	@RequestMapping(value = "/deleteEmployee/{employeeId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Get Employee", notes = "Returns Response Of Employee. SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Created Employee"),
			@ApiResponse(code = 404, message = "Creation Of Employee Failed"),
			@ApiResponse(code = 400, message = "Invalid Input Provided") })
	public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable("employeeId") Long employeeId)throws ApplicationException, DataNotFoundException {

		EmployeeResponse response = new EmployeeResponse();
		Boolean returnCreateStatus = false;
		String createStatusCode = null;
		String createstatusMsg = null;
		
		boolean isDel = employeeService.deleteEmployee(employeeId);
		if (isDel == true) {
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
			LOGGER.debug("Employee details deleted successfully");
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}

	/**
	 * @param employeeDTO
	 * @param operationType
	 * @throws ApplicationException
	 */
	private void validateEmployee(EmployeeDTO employeeDTO, String operationType) throws ApplicationException {
		String validateStatus = employeeUtils.validateEmployee(employeeDTO, operationType);
		if (validateStatus != null) {
			throw new ApplicationException(StatusCode.BAD_REQUEST.getCode(), validateStatus);
		}
	}
}
