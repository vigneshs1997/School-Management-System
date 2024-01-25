package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.requestdto.SubjectRequest;
import com.school.responsedto.AcademicProgramResponse;
import com.school.responsedto.SubjectResponse;
import com.school.service.SubjectService;
import com.school.util.ResponseStructure;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@PostMapping("/academic-programs/{programId}/subjects")
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>>addSubject(@PathVariable int programId,@RequestBody SubjectRequest subjectRequest)
	{
		return subjectService.addSubject(programId,subjectRequest);
	}

	@GetMapping("/subjects")
	public ResponseEntity<ResponseStructure<List<SubjectResponse>>>findAllSubjects()
	{
		return subjectService.findAllSubjects();
	}
}
