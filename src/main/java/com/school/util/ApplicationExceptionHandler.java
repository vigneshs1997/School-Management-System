package com.school.util;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.school.exception.AcademicProgamNotFoundException;
import com.school.exception.AdminAlreadyExistException;
import com.school.exception.AdminNotFoundException;
import com.school.exception.ScheduleObjectNotFoundException;
import com.school.exception.SchoolAlreadyExistException;
import com.school.exception.SchoolObjectNotFoundException;
import com.school.exception.UserObjectNotFoundByIdException;

@RestControllerAdvice// It is used to allow the exception handler in the UI (It gives advice to UI)
public class ApplicationExceptionHandler{


	public ResponseEntity<Object> structure (HttpStatus status,String message,Object rootCause)
	{  //Object => It can accept any entity class only
		return new ResponseEntity<Object> (Map.of(
				"status",status.value(),
				"message",message,
				"rootCause",rootCause
				),status);
	}
/*====================================HANDLING THE  AdminAlreadyExistException=============================================================*/
	@ExceptionHandler(AdminAlreadyExistException.class)
	public ResponseEntity<Object> handleadminAlreadyExist(AdminAlreadyExistException ex)
	{
		return structure(HttpStatus.BAD_REQUEST, ex.getMessage(), "admin already exist");
	}

/*====================================HANDLING THE  UserObjectNotFoundException=============================================================*/

	@ExceptionHandler(UserObjectNotFoundByIdException.class)
	public ResponseEntity<Object> handleuserObjectNotFoundById(UserObjectNotFoundByIdException ex)
	{
		return structure(HttpStatus.NOT_FOUND,ex.getMessage(),"user not found by the specified Id");
	}
/*=========================================HANDLING THE  AdminNotFoundException============================================================*/
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<Object> handleadminNotFound(AdminNotFoundException ex)
	{
		return structure(HttpStatus.NOT_FOUND, ex.getMessage(), "admin not found by the specified Id ");
	}
/*=========================================HANDLING THE  SchoolObjectNotFoundException============================================================*/
	@ExceptionHandler(SchoolObjectNotFoundException.class)
	public ResponseEntity<Object> handleschoolObjectNotFoundById(SchoolObjectNotFoundException ex)
	{
		return structure(HttpStatus.NOT_FOUND,ex.getMessage(),"school not found by  the specified Id");
	}
/*=========================================HANDLING THE  SchoolAlreadyExistException============================================================*/

	@ExceptionHandler(SchoolAlreadyExistException.class)
	public ResponseEntity<Object> handleschoolAlreadyExist(SchoolAlreadyExistException ex)
	{
		return structure(HttpStatus.FOUND,ex.getMessage(), "school already exist");
	}
/*=========================================HANDLING THE  ScheduleObjectNotFoundException============================================================*/
	@ExceptionHandler(ScheduleObjectNotFoundException.class)
	public ResponseEntity<Object> handlescheduleObjectNotFoundById(ScheduleObjectNotFoundException ex)
	{
		return structure(HttpStatus.NOT_FOUND, ex.getMessage()," schedule not found by the specified Id" );
	}
/*=========================================HANDLING THE  AcademicProgamNotFoundException============================================================*/
	@ExceptionHandler(AcademicProgamNotFoundException.class)
	public ResponseEntity<Object> handleacademicProgramNotFoundById(AcademicProgamNotFoundException ex)
	{
		return structure(HttpStatus.NOT_FOUND, ex.getMessage(), "academic Program Not Found By the specified Id");
	}
	
}
