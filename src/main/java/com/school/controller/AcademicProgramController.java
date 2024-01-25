package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.requestdto.AcademicProgramRequest;
import com.school.responsedto.AcademicProgramResponse;
import com.school.service.AcademicProgramService;
import com.school.util.ResponseStructure;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class AcademicProgramController {
	@Autowired
	private AcademicProgramService academicProgramService;


	@PostMapping("/schools/{schoolId}/academic-programs")
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>>saveAcademicProgram(@PathVariable int schoolId,@RequestBody AcademicProgramRequest academicProgramRequest)
	{
		return academicProgramService.saveAcademicProgram(schoolId,academicProgramRequest);
	}


	@GetMapping("/schools/{schoolId}/academic-programs")
	public ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>>findAcademicProgram(@PathVariable int schoolId)
	{
		return academicProgramService.findAcademicProgram(schoolId);
	}

}
