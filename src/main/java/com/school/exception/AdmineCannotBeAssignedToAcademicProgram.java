package com.school.exception;

import lombok.Getter;

@Getter
public class AdmineCannotBeAssignedToAcademicProgram extends RuntimeException {

	private String message;

	public AdmineCannotBeAssignedToAcademicProgram(String message)
	{
		this.message=message;
	}
}
