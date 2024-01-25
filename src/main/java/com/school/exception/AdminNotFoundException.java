package com.school.exception;

import lombok.Getter;

@Getter
public class AdminNotFoundException extends RuntimeException {

	private String message;

	public AdminNotFoundException(String message)
	{
		this.message=message;
	}
}
