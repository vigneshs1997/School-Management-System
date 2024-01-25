package com.school.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.Repository.AcademicProgramRepository;
import com.school.Repository.SubjectRepository;
import com.school.Repository.UserRepository;
import com.school.entity.AcademicProgram;
import com.school.entity.Subject;
import com.school.entity.User;
import com.school.enums.UserRole;
import com.school.exception.AcademicProgamNotFoundException;
import com.school.exception.AdminAlreadyExistException;
import com.school.exception.AdminCannotBeAssignedToAcademicProgram;
import com.school.exception.AdminNotFoundException;
import com.school.exception.AdmineCannotBeAssignedToAcademicProgram;
import com.school.exception.OnlyTeacherCanBeAssignedToSubjectException;
import com.school.exception.SubjectNotFoundException;
import com.school.exception.UserObjectNotFoundByIdException;

import com.school.requestdto.UserRequest;
import com.school.responsedto.UserResponse;
import com.school.service.UserService;
import com.school.util.ResponseStructure;

import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertFalse.List;

@Service //class to interface =>implements
public class UserSeviceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder encoder;
    
	@Autowired //to create an object of  UserRepository
	private UserRepository userRepository;
	
	@Autowired //to create an object of responseStructure
	private ResponseStructure<UserResponse> responseStructure;
	
	@Autowired
	private AcademicProgramRepository academicProgramRepository;
	
	@Autowired
	private SubjectRepository  subjectRepository ;
	
	//=====================================================MAPPING==============================================================================	
    /*map=> 1. getting userRequest and storing in database through User Entity class because database accepts entity class only
	        2. mapping userRequest to User Entity class*/
	private User mapToUser(UserRequest request)
	{
		//building relationship between userRequest to user
		return User.builder()
				.userName(request.getUserName())
				.password(encoder.encode(request.getPassword()))
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.contactNo(request.getContactNo())
				.email(request.getEmail())
				.userRole(request.getUserRole())
				.build() ;
		
	}
	   /*map=> 1. getting data from the database  and showing that data in the UI through entity class
               2. mapping User  to  userReponse*/
	private UserResponse mapToUserResponse(User response) {
		return UserResponse.builder()
				.userId(response.getUserId())
				.userName(response.getUserName())
				.firstName(response.getFirstName())
				.lastName(response.getLastName())
				.contactNo(response.getContactNo())
				.email(response.getEmail())
				.userRole(response.getUserRole())
				.build() ;
		
	}
//===========================================================registerAdmin===========================================================
//		@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(UserRequest userRequest) {
		
		if(userRequest.getUserRole().equals(UserRole.ADMIN)) {
			
			if (userRepository.existsByIsDeletedAndUserRole(false , userRequest.getUserRole()))  {
				throw new AdminAlreadyExistException("Admin already exist");
			} 
			else {
				if(userRepository.existsByIsDeletedAndUserRole(true, userRequest.getUserRole())) {
					User user = userRepository.save(mapToUser(userRequest));

					responseStructure.setStatus(HttpStatus.CREATED.value());
					responseStructure.setMessage("user saved successfully");
					responseStructure.setData(mapToUserResponse(user));

					return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);
				}
				else {
					User user = userRepository.save(mapToUser(userRequest));

					responseStructure.setStatus(HttpStatus.CREATED.value());
					responseStructure.setMessage("user saved successfully");
					responseStructure.setData(mapToUserResponse(user));

					return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);	
				}
			}
		}
		else {
			throw new AdminNotFoundException("admin not found");
		}
		
	}
	
//====================================================addOtherUser===================================================================	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addOtherUser(UserRequest userRequest) {
		
		if(userRequest.getUserRole().equals(UserRole.ADMIN)) {
			throw new AdminAlreadyExistException("admin already found");
		}
		else {
			User user = userRepository.save(mapToUser(userRequest));

			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("user saved successfully");
			responseStructure.setData(mapToUserResponse(user));

			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);
		}
	}
//=================================================findUser==================================================================
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUser(Integer userId) 
	{

		User user = userRepository.findById(userId)
				.orElseThrow(()->new UserObjectNotFoundByIdException("user not found"));

		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("user found successfully");
		responseStructure.setData(mapToUserResponse(user));


		return new  ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.FOUND);
	}
//=================================================deleteUser======================================================================	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(Integer userId)
	{
		User user =userRepository.findById(userId)
				.orElseThrow(()->new UserObjectNotFoundByIdException("user not found"));
        //if user is there, false(0) comes AND fetching value from user class by using isDeleted()
		
		if(user.isDeleted()==true) //0==1=>0 not equals 1S
		{
			throw new UserObjectNotFoundByIdException("user not found");
		}
        
		user.setDeleted(true);//setting value 1 if user exists
		User save = userRepository.save(user);


		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("user deleted successfully");
		responseStructure.setData(mapToUserResponse(save));


		return new  ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);
	}
//=======================================================updateUser===============================================================================
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId, UserRequest userRequest) {

		return userRepository.findById(userId)
				.map( user -> {
					User mappedUser = mapToUser(userRequest);
					mappedUser.setUserId(userId);
					user = userRepository.save(mappedUser);
					
					responseStructure.setStatus(HttpStatus.OK.value());
					responseStructure.setMessage("user updated successfully");
					responseStructure.setData(mapToUserResponse(user));

					return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
				})
				.orElseThrow(() -> new UserObjectNotFoundByIdException("user not found"));
	}

//==========================================================assignUser===============================================================
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> assignUser(int userId, int programId) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new UserObjectNotFoundByIdException("user not found"));

		AcademicProgram academicProgram = academicProgramRepository.findById(programId)
				.orElseThrow(()->new AcademicProgamNotFoundException("AcademicProgam not found"));

		if(user.getUserRole().equals(UserRole.ADMIN))
		{
			throw new AdmineCannotBeAssignedToAcademicProgram("admin cannot assign");
		}
		else
		{
			user.getAcademicProgram().add(academicProgram);
			userRepository.save(user);
			academicProgram.getUsers().add(user);
			academicProgramRepository.save(academicProgram );

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("user associated with academic program successfully");
			responseStructure.setData(mapToUserResponse(user));


			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);
		}
	}
//=======================================================addSubjectToTheTeacher======================================================
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addSubjectToTheTeacher(int subjectId, int userId) {
		
		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(()-> new SubjectNotFoundException("subject not found"));

		User user = userRepository.findById(userId)
				.orElseThrow(()-> new UserObjectNotFoundByIdException("user not found"));

		if(user.getUserRole().equals(UserRole.TEACHER))
		{

			user.setSubject(subject);
			userRepository.save(user);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("subject added to the teacher successfully");
			responseStructure.setData(mapToUserResponse(user));

			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);

		}
		else
		{
			throw new OnlyTeacherCanBeAssignedToSubjectException("user is not a teacher");
		}
	}
//===================================(Assigning teacher and studnet to AcademicProgram)==============================================


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> assignToAcademicProgram(int programId, int userId) {

		return userRepository.findById(userId)
				.map(user -> {
					if(user.getUserRole().equals(UserRole.ADMIN)) {
						throw new AdmineCannotBeAssignedToAcademicProgram("admin cannot be assigned");
					}
					else {
						return academicProgramRepository.findById(programId)
								.map(academicProgram -> {
									academicProgram.getUsers().add(user);
									user.getListOfAcademicPrograms().add(academicProgram);

									userRepository.save(user);
									academicProgramRepository.save(academicProgram);

									responseStructure.setStatus(HttpStatus.OK.value());
									responseStructure.setMessage("user updated successfully");
									responseStructure.setData(mapToUserResponse(user));

									return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);

								})
								.orElseThrow(() -> new AcademicProgamNotFoundException("academic program not found"));
					}
				})
				.orElseThrow(() -> new UserObjectNotFoundByIdException("user not found"));

	}

//=================================================assignSubjectToTeacher=============================================================	

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> assignSubjectToTeacher(int subjectId, int userId) {
		return userRepository.findById(userId)
				.map(user -> {
					if(user.getUserRole().equals(UserRole.TEACHER)) {
						return subjectRepository.findById(subjectId)
								.map(subject -> {
									user.setSubject(subject);
									userRepository.save(user);

									responseStructure.setStatus(HttpStatus.OK.value());
									responseStructure.setMessage("subject assigned to teacher successfully");
									responseStructure.setData(mapToUserResponse(user));

									return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
								})
								.orElseThrow(() -> new SubjectNotFoundException("subject not found"));
					}
					else {
						throw new OnlyTeacherCanBeAssignedToSubjectException("only teacher can be assigned to the subject");
					}
				})
				.orElseThrow(() -> new UserObjectNotFoundByIdException("user not found"));
	}

//====================================================================================================================================
//if we access many,we need to give map
//if we access one,we do not need to give map
	
}

