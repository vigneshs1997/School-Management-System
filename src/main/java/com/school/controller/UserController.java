package com.school.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.requestdto.UserRequest;
import com.school.responsedto.UserResponse;
import com.school.service.UserService;
import com.school.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController//(It is advanced version of @Controller and stereotype annotations)=>UI approaches here only
public class UserController {
                                    /*Here we have handler methods*/
	@Autowired//for creating an object of userService
	private UserService userService;
	
	
	
	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(@RequestBody UserRequest userRequest){
		return userService.registerAdmin(userRequest);
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> addOtherUser(@RequestBody UserRequest userRequest){
		return userService.addOtherUser(userRequest);
	}
	
	
	
	@GetMapping("/users/{userId}")
	// @PathVariable is used to trace using userId
	public ResponseEntity<ResponseStructure<UserResponse>> findUser(@PathVariable Integer userId)
	{
		return userService.findUser(userId);
	}
	@PreAuthorize("hasAuthority('ADMIN')")//ADMIN only can delete 
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>>deleteUser(@PathVariable Integer userId)
	{
		return userService.deleteUser(userId);
	}
	

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable("userId") int userId,
			@RequestBody UserRequest userRequest){
		return userService.updateUser(userId, userRequest);
	}
		
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/academic-programs/{programId}/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> assignToAcademicProgram(@PathVariable("programId") int programId,
			@PathVariable("userId") int userId){
		return userService.assignToAcademicProgram(programId, userId);
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/subjects/{subjectId}/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> assignSubjectToTeacher(@PathVariable("subjectId") int subjectId,
			@PathVariable("userId") int userId){
		return userService.assignSubjectToTeacher(subjectId,userId);
	}

	
	
}

/*@ResponseEntity = it is use to give in proper structure and it accepts generics*/
/*@RequestBody= It is used to convert (json to java)and(java to json)*/
/*@Valid = It is used for validation*/
/*@PathVariable = It works*/

