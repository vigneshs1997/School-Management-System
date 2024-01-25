package com.school.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.school.Repository.SchoolRepository;
import com.school.Repository.UserRepository;
import com.school.entity.School;
import com.school.entity.User;
import com.school.enums.UserRole;
import com.school.exception.AdminNotFoundException;
import com.school.exception.SchoolAlreadyExistException;
import com.school.exception.SchoolObjectNotFoundException;
import com.school.exception.UserObjectNotFoundByIdException;

import com.school.requestdto.SchoolRequest;
import com.school.responsedto.SchoolResponse;
import com.school.service.SchoolService;
import com.school.util.ResponseStructure;
@Repository
public class SchoolServiceImpl implements SchoolService{
	@Autowired
	private UserRepository  userRepository;

	@Autowired	
	private SchoolRepository schoolRepository;

	@Autowired
	private ResponseStructure<SchoolResponse> responseStructure;

	@Autowired
	private ResponseStructure<List<SchoolResponse>> structure;
/*=====================================================MAPPING PART===========================================================*/

	private School mapToSchoolRequest(SchoolRequest schoolRequest)
	{
		return School.builder()
				.schoolName(schoolRequest.getSchoolName())
				.contactNo(schoolRequest.getContactNo())
				.emailId(schoolRequest.getEmailId())
				.address(schoolRequest.getAddress())
				.build();
	}

	private SchoolResponse mapToSchoolResponse(School school)
	{
		return SchoolResponse.builder()
				.schoolId(school.getSchoolId())
				.schoolName(school.getSchoolName())
				.contactNo(school.getContactNo())
				.emailId(school.getEmailId())
				.address(school.getAddress())
				.build();
	}
/*================================================LOGICAL PART=============================================================================*/
	                      /*===========================saving schools===================================*/
	@Override
	public ResponseEntity<ResponseStructure<SchoolResponse>> saveSchool(SchoolRequest school) 
	{
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		//if Id already exists or not
		User user = userRepository.findByUserName(userName)
				.orElseThrow(()-> new UserObjectNotFoundByIdException("user not found"));
        //if user role is admin or not
		if(user.getUserRole().equals(UserRole.ADMIN))
		{   
			//if school for user already exists or not
			if(user.getSchool()==null)
			{   //saving all data given by amin through userResponse to the database of school
				School save = schoolRepository.save(mapToSchoolRequest(school));
				user.setSchool(save);
				userRepository.save(user);

				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("School  Created");
				responseStructure.setData(mapToSchoolResponse(save));

				return new  ResponseEntity<ResponseStructure<SchoolResponse>>(responseStructure,HttpStatus.CREATED);
			}
			else
			{

				throw new SchoolAlreadyExistException("school already exist");
			}

		}
		else
		{
			throw new AdminNotFoundException("admin not found");
		}


	}
//                                   /*============================UPDATE THE SCHOOL==================================*/

	@Override
	public ResponseEntity<ResponseStructure<SchoolResponse>> updateSchool(int schoolId, SchoolRequest school) 
	{
		School save = schoolRepository.findById(schoolId)
				.map(u->{
					School mapToSchoolRequest = mapToSchoolRequest(school);
					mapToSchoolRequest.setSchoolId(schoolId);
					return schoolRepository.save(mapToSchoolRequest);
				})
				.orElseThrow(()-> new SchoolObjectNotFoundException("School Not Found"));


		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("School Updated");
		responseStructure.setData(mapToSchoolResponse(save));

		return new ResponseEntity<ResponseStructure<SchoolResponse>>(responseStructure,HttpStatus.OK);
	}

	                              /*===============================findAllSchool===============================*/

	@Override
	public ResponseEntity<ResponseStructure<List<SchoolResponse>>> findAllSchool()  //List for many schools
	{
		List<School> findAll = schoolRepository.findAll();

		List<SchoolResponse> collect = findAll.stream()
				.map(u-> mapToSchoolResponse(u))
				.collect(Collectors.toList());
       //If we need exception,we can give exception

		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("School Found");
		structure.setData(collect );

		return new ResponseEntity<ResponseStructure<List<SchoolResponse>>>(structure,HttpStatus.FOUND);
	}
	                             /*================================deleteSchool==============================*/
	@Override
	public ResponseEntity<ResponseStructure<SchoolResponse>>deleteSchool(int schoolId) 
	{
		School save = schoolRepository.findById(schoolId)
				                     //If we need exception,we can give exception
				.orElseThrow(()->new SchoolObjectNotFoundException("School Not Found"));

		
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("school deleted");
		responseStructure.setData(mapToSchoolResponse(save));

		return new ResponseEntity<ResponseStructure<SchoolResponse>>(responseStructure,HttpStatus.OK);
	}
//===================================================================================================================

	@Override
	public ResponseEntity<ResponseStructure<SchoolResponse>> findSchoolById(int schoolId) 
	{
		School save = schoolRepository.findById(schoolId
				).orElseThrow(()-> new SchoolObjectNotFoundException("School Not Found"));


		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("School found");
		responseStructure.setData(mapToSchoolResponse(save));

		return new ResponseEntity<ResponseStructure<SchoolResponse>>(responseStructure,HttpStatus.FOUND);

	}
	

}
