package com.school.exception;

import lombok.Getter;

@Getter
public class OnlyTeacherCanBeAssignedToSubjectException extends RuntimeException {

	private String message;

	public OnlyTeacherCanBeAssignedToSubjectException(String message)
	{
		this.message=message;
	}
}
