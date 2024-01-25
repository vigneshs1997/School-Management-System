package com.school.exception;

import lombok.Getter;

@Getter
public class ScheduleObjectNotFoundException extends RuntimeException {

	private String message;

	public ScheduleObjectNotFoundException(String message)
	{
		this.message=message;
	}
}
