package com.school.exception;

import lombok.Getter;

@Getter
public class SubjectNotFoundException extends RuntimeException {

	private String message;

	public SubjectNotFoundException(String message)
	{
		this.message=message;
	}
}
