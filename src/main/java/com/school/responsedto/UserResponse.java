package com.school.responsedto;

import java.util.List;

import com.school.enums.UserRole;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
	
	 private int userId;
	 private String userName;
	 private String firstName;
	 private String lastName;
	 private long contactNo;
	 private String email;
	 private UserRole userRole;
	 //private List<String>listAcademicProgram;
     
}
