package com.newt.lms.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.newt.lms.constants.EmployeeConstants;
import com.newt.lms.model.jpa.dto.EmployeeDTO;

/**
 * @author nareshd
 *
 */
@Component
public class EmployeeUtils {

	public String validateCreateEmployee(EmployeeDTO employeeDTO) {
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
