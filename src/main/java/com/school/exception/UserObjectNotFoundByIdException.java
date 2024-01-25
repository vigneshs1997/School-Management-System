package com.school.exception;

import lombok.Getter;

public class UserObjectNotFoundByIdException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public UserObjectNotFoundByIdException(String message)
	{
		this.message=message;
	}
	//Insted of using getter annotation
	public String getMessage()
	{
		return message;
	}

}
