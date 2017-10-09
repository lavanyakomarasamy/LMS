package com.newt.lms.controller;

import java.util.List;

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
import com.newt.lms.model.jpa.dao.Login;
import com.newt.lms.model.jpa.dao.LoginReq;
import com.newt.lms.model.jpa.dao.LoginResponse;
import com.newt.lms.model.jpa.dao.ResponseStatus;
import com.newt.lms.service.EmployeeService;
import com.newt.lms.service.LoginService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/LMS")
public class LoginController {
	/**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private EmployeeService employeeService;
    /**
     * Login
     * 
     * @param login
     * @return
     */
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    @CrossOrigin(origins = { "*" })
	@ApiOperation(value = "Login", notes = "Returns A Response Of Login. SLA:500", response = LoginResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Logged In", response = Login.class),
			@ApiResponse(code = 400, message = "Invalid Input Provided"),
			@ApiResponse(code = 404, message = "Login Details Does Not Exist") })
	public ResponseEntity<LoginResponse> login(@RequestBody Login loginReq) {
    	LoginResponse response = new LoginResponse();
    	Boolean returnStatus = false;
		String statusCode = null;
		String statusMsg = null;
		List<Login> login = null;
		
    	login = loginService.getLogin(loginReq);
		if (login != null && !login.isEmpty()) {
			returnStatus = true;
			statusMsg = StatusCode.SUCCESS.getDesc();
			statusCode = StatusCode.SUCCESS.getCode();

		} else {
			statusMsg = StatusCode.DATA_NOT_FOUND.getDesc();
			statusCode = StatusCode.DATA_NOT_FOUND.getCode();
		}
    	ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setCode(statusCode);
		responseStatus.setDescription(statusMsg);
		
		response.setStatus(responseStatus);
		response.setLoginList(login);

		if (returnStatus) {
			LOGGER.debug(" Login successful");
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
    }   
    
    
    /**
     * Create LoginCredentials
     * 
     * @param LoginCredentials
     * @return
     */
    @RequestMapping(value = "/createLoginCredentials", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins = { "*" })
    @ApiOperation(value = "Create LoginCredentials", notes = "Returns a response of LoginCredentials creation. SLA:500", response = LoginResponse.class)
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful Creation Of LoginCredentials", response = LoginResponse.class),
	    @ApiResponse(code = 404, message = "Creation Of Login Credentials Failed"),
	    @ApiResponse(code = 400, message = "Invalid Input Provided") })
    public ResponseEntity<LoginResponse> createLoginCredentials(@RequestBody LoginReq loginJson) {
    	
    	Login loginRequest;
    	Login login = null;
    	String createStatusCode = null;
		String createstatusMsg = null;
		LoginResponse response = new LoginResponse();
    	Boolean returnCreateStatus = false;
		
		try {
			loginRequest = setRequest(loginJson);
			login = loginService.createLogin(loginRequest);
			returnCreateStatus = true;
			if (login != null) {
				createStatusCode = StatusCode.SUCCESS.getCode();
				createstatusMsg = StatusCode.SUCCESS.getDesc();

			} else {
				createStatusCode = StatusCode.DATA_NOT_FOUND.getCode();
				createstatusMsg = StatusCode.DATA_NOT_FOUND.getDesc();
			}
		} catch (ApplicationException e) {
			createstatusMsg = e.getMessage();
			createStatusCode = e.getErrCode();
			e.printStackTrace();
		}
		
    	ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setCode(createStatusCode);
		responseStatus.setDescription(createstatusMsg);
		response.setStatus(responseStatus);
		
    	if (returnCreateStatus) {
    		response.setLogin(login);
    		LOGGER.debug("Login Credentials created successfully");
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
    }
    
    private Login setRequest(LoginReq loginJson) throws ApplicationException {
    	Login login = new Login();
    	
    	Employee emp = new Employee();
    	emp.setEmployeeId(loginJson.getEmployeeId());
    	Employee empRes = employeeService.getEmployee(emp);
    	if (empRes != null) {
    		login.setEmployee(empRes);
    	} else {
    		throw new ApplicationException(StatusCode.APP_ERROR.getCode(),
					"Application Error. Not able to Get Employee Details");
    	}
    	
    	login.setPassword(loginJson.getPassword());
    	
    	login.setUserId(loginJson.getUserId());
    	System.out.println("-----------------"+loginJson);
    	return login;
    
    }
    
}
