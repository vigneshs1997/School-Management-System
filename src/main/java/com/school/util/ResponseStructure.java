package com.school.util;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component

//T can accepts data types 
public class ResponseStructure<T> {

	int status;
	String message;
	T data;
}
