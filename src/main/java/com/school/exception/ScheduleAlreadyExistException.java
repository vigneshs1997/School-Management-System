package com.school.exception;

import lombok.Getter;

@Getter
public class ScheduleAlreadyExistException extends RuntimeException {

	private String message;

	public ScheduleAlreadyExistException(String message)
	{
		this.message=message;
	}
}
