package com.school.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.requestdto.AcademicProgramRequest;
import com.school.responsedto.AcademicProgramResponse;
import com.school.util.ResponseStructure;

public interface AcademicProgramService  {

	ResponseEntity<ResponseStructure<AcademicProgramResponse>> saveAcademicProgram(int schoolId,AcademicProgramRequest academicProgramRequest);
	ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> findAcademicProgram(int schoolId);
}
