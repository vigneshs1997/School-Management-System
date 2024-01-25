package com.school.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.requestdto.SubjectRequest;
import com.school.responsedto.AcademicProgramResponse;
import com.school.responsedto.SubjectResponse;
import com.school.util.ResponseStructure;

public interface SubjectService {

	ResponseEntity<ResponseStructure<AcademicProgramResponse>> addSubject(int programId,SubjectRequest subjectRequest);
	ResponseEntity<ResponseStructure<List<SubjectResponse>>> findAllSubjects();
}
