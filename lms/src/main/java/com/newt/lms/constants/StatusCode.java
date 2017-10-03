package com.newt.lms.constants;

public enum StatusCode {
	
	SUCCESS("0", "Success"), 
    APP_ERROR("601", "Application Error"), 
    BAD_REQUEST("400", "Bad Request"), 
    DATA_NOT_FOUND("604","Data Not Found"),
    INTERNAL_ERROR("605","Internal Error"),
    DATA_HANDLING_ERROR("606","Data Handling Error"),
    NOT_CREATED("607","Data Not Created"),
    NOT_UPDATED("608","Data Not Updated"),
    BONITA_PROCESS_ERROR("609","Bonita Process/Service Error");	

	private final String code;
	private final String desc;

	private StatusCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * Return the String value of this status code.
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Return the message of this status code.
	 */
	public String getDesc() {
		return this.desc;
	}
}
