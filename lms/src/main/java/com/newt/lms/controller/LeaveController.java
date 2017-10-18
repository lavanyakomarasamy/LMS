package com.newt.lms.controller;


import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newt.lms.model.jpa.dao.Leave;
import com.newt.lms.model.jpa.dao.LeaveResponse;

@RestController
@RequestMapping("/LMS")
public class LeaveController {
	
	public static final Logger LOGGER = Logger.getLogger(LeaveController.class);
	
	@RequestMapping(value = "/createLeave", method = RequestMethod.POST, consumes = "application/json", produces="application/json")
	public ResponseEntity<LeaveResponse>createLeave(@RequestBody Leave leave)
	{
		return null;
		
	}
	
	@RequestMapping(value = "/updateLeave", method = RequestMethod.PUT, consumes = "application/json", produces="application/json")
	public ResponseEntity<LeaveResponse>updateLeave(@RequestBody Leave leave)
	{
		return null;
		
	}
	
	@RequestMapping(value = "/getLeave", method = RequestMethod.POST, consumes = "application/json", produces="application/json")
	public ResponseEntity<LeaveResponse>getLeave(@RequestBody Leave leave)
	{
		return null;
		
	}
	
	@RequestMapping(value = "/deleteLeave", method = RequestMethod.POST, consumes = "application/json", produces="application/json")
	public ResponseEntity<LeaveResponse>deleteLeave(@RequestBody Leave leave)
	{
		return null;
		
	}

}
