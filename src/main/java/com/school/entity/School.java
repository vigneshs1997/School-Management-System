package com.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
	@Id //=> It is primary key field
	@GeneratedValue(strategy = GenerationType.IDENTITY) //=>the database should automatically generate the values for this field.
	
	private int schoolId;
	private String schoolName;
	private long ContactNo;
	private String emailId;
	private String Address;
	 
}
