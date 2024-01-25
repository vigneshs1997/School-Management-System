package com.school.exception;

import lombok.Getter;

@Getter
public class SchoolObjectNotFoundException extends RuntimeException {

	private String message;

	public SchoolObjectNotFoundException(String message)
	{
		this.message=message;
	}
}
