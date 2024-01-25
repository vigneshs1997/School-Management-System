package com.school.exception;

import lombok.Getter;

@Getter
public class SchoolAlreadyExistException extends RuntimeException {

	private String message;

	public SchoolAlreadyExistException(String message)
	{
		this.message=message;
	}
	
}
