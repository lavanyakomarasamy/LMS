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

import com.newt.lms.constants.StatusCode;
import com.newt.lms.exception.ApplicationException;
import com.newt.lms.model.jpa.dao.Employee;
import com.newt.lms.model.jpa.dao.EmployeeResponse;
import com.newt.lms.model.jpa.dao.LoginResponse;
import com.newt.lms.model.jpa.dao.ResponseStatus;
import com.newt.lms.model.jpa.dto.EmployeeDTO;
import com.newt.lms.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/LMS")
public class EmployeeController {
	/**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);
    
    @Autowired
    private EmployeeService employeeService;
    
    /**
     * Create Employee
     * 
     * @param EmployeeDTO
     * @return
     * @throws ApplicationException 
     */
    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins = { "*" })
    @ApiOperation(value = "Create Employee", notes = "Returns a response of Employee creation. SLA:500", response = EmployeeResponse.class)
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful Creation Of Employee", response = EmployeeResponse.class),
	    @ApiResponse(code = 404, message = "Creation Of LEmployee Failed"),
	    @ApiResponse(code = 400, message = "Invalid Input Provided") })
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeDTO employeeDTO) throws ApplicationException {
    	
    	EmployeeResponse response = new EmployeeResponse();
    	Boolean returnCreateStatus = false;
		String createStatusCode = null;
		String createstatusMsg = null;
    	
		validateCreateEmployee(employeeDTO);
		
		Employee employee = employeeService.createEmployee(employeeDTO);
		returnCreateStatus = true;
		if (employee != null) {
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
		
		int empId = employeeDTO.getEmployeeId();
		String empName = employeeDTO.getEmployeeName();
		Date doj = employeeDTO.getDoj();
		Date dob = employeeDTO.getDob();
				
		if(empId == 0 || (empName.isEmpty() || empName == null) || (doj == null || dob == null))
		{
			LOGGER.debug("Mandatory values are missing ");
			throw new ApplicationException("407", "Mandatory values are missing");
		}
		
	}    
}
