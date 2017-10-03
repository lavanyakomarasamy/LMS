package com.newt.lms.controller;

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
import com.newt.lms.model.Employee;
import com.newt.lms.model.EmployeeResponse;
import com.newt.lms.model.LoginResponse;
import com.newt.lms.model.ResponseStatus;
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
     * @param Employee
     * @return
     */
    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins = { "*" })
    @ApiOperation(value = "Create Employee", notes = "Returns a response of Employee creation. SLA:500", response = LoginResponse.class)
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful Creation Of Employee", response = LoginResponse.class),
	    @ApiResponse(code = 404, message = "Creation Of LEmployee Failed"),
	    @ApiResponse(code = 400, message = "Invalid Input Provided") })
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody Employee employeeJson) {
    	
    	EmployeeResponse response = new EmployeeResponse();
    	Boolean returnCreateStatus = false;
		String createStatusCode = null;
		String createstatusMsg = null;
    	
		Employee employee = employeeService.createEmployee(employeeJson);
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
    		LOGGER.debug("Login Credentials created successfully");
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
    }    
}
