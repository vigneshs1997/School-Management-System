package com.school.exception;

import lombok.Getter;

@Getter
public class AcademicProgamNotFoundException  extends RuntimeException{

	private String message;

	public AcademicProgamNotFoundException(String message)
	{
		this.message=message;
	}
}
