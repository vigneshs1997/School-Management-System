package com.school.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.requestdto.SchoolRequest;
import com.school.requestdto.UserRequest;
import com.school.responsedto.SchoolResponse;
import com.school.responsedto.UserResponse;
import com.school.util.ResponseStructure;

public interface SchoolService {

	ResponseEntity<ResponseStructure<SchoolResponse>> saveSchool( SchoolRequest school);

	ResponseEntity<ResponseStructure<SchoolResponse>> updateSchool(int schoolId, SchoolRequest school);

	ResponseEntity<ResponseStructure<List<SchoolResponse>>> findAllSchool();

	ResponseEntity<ResponseStructure<SchoolResponse>> deleteSchool(int schoolId);

	ResponseEntity<ResponseStructure<SchoolResponse>> findSchoolById(int schoolId);

	
}
