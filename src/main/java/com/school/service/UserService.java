package com.school.service;

import org.springframework.http.ResponseEntity;

import com.school.requestdto.UserRequest;
import com.school.responsedto.UserResponse;
import com.school.util.ResponseStructure;

import jakarta.validation.Valid;

public interface UserService {
  //=================================================creating methods========================================================
	

	ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(UserRequest userRequest);
	
	ResponseEntity<ResponseStructure<UserResponse>> addOtherUser(UserRequest userRequest);
	
	ResponseEntity<ResponseStructure<UserResponse>> findUser(Integer userId);

	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(Integer userId);
	
	ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId, UserRequest userRequest);

	ResponseEntity<ResponseStructure<UserResponse>> assignUser(int userId, int programId);

	ResponseEntity<ResponseStructure<UserResponse>> addSubjectToTheTeacher(int subjectId, int userId);
	
	ResponseEntity<ResponseStructure<UserResponse>> assignToAcademicProgram(int programId, int userId);

	ResponseEntity<ResponseStructure<UserResponse>> assignSubjectToTeacher(int subjectId, int userId);

	

	

	
	
}
