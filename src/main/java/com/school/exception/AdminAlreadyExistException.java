package com.school.exception;

import lombok.Getter;

@Getter
//Exception in exception class throw exception at the time only so we need to handle now by  using try and catch block
//but RuntimeException in exception class throws exception after compilation only so we don not need to handle now.
public class AdminAlreadyExistException extends RuntimeException {

	private String message;

	public AdminAlreadyExistException(String message)
	{
		this.message=message;
	}

}
