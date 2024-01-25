package com.school.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.Repository.AcademicProgramRepository;
import com.school.Repository.SchoolRepository;
import com.school.entity.AcademicProgram;
import com.school.entity.School;
import com.school.entity.Subject;
import com.school.exception.SchoolObjectNotFoundException;
import com.school.requestdto.AcademicProgramRequest;
import com.school.responsedto.AcademicProgramResponse;
import com.school.service.AcademicProgramService;
import com.school.util.ResponseStructure;

@Service
public class AcademicProgramServiceImpl implements AcademicProgramService{


	@Autowired
	private AcademicProgramRepository academicProgramRepository;


	@Autowired
	private SchoolRepository schoolRepository;


	@Autowired
	private ResponseStructure<AcademicProgramResponse> responseStructure;

	@Autowired
	private ResponseStructure<List<AcademicProgramResponse>> ListResponseStructure;

/*===========================================================MAPPING PART====================================================================*/
	private AcademicProgram mapToAcademicProgramRequest(AcademicProgramRequest academicProgramRequest)
	{
		return AcademicProgram.builder()
				.programType(academicProgramRequest.getProgramType())
				.programName(academicProgramRequest.getProgramName())
				.beginsAt(academicProgramRequest.getBeginsAt())
				.endsAt(academicProgramRequest.getEndsAt())
				.build();
	}

	public AcademicProgramResponse mapToAcademicProgramResponse(AcademicProgram academicProgram )
	{
		List<String>subjects= new ArrayList<String>();
		List<Subject>listOfSubjects=academicProgram.getSubjects();

		if(listOfSubjects!=null)
		{
			listOfSubjects.forEach(sub->{
				subjects.add(sub.getSubjectNames());
			});
		}

		return AcademicProgramResponse.builder()
				.programId(academicProgram.getProgramId())
				.programType(academicProgram.getProgramType())
				.programName(academicProgram.getProgramName())
				.beginsAt(academicProgram.getBeginsAt())
				.endsAt(academicProgram.getEndsAt())
				.subjects(academicProgram.getSubjects())
				.build();
	}

	/*===========================================================saveAcademicProgram==========================================================*/

	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> saveAcademicProgram(int schoolId,
			AcademicProgramRequest academicProgramRequest) {

		School school = schoolRepository.findById(schoolId)
				.orElseThrow(()-> new SchoolObjectNotFoundException("school not found"));

		AcademicProgram academicProgram = academicProgramRepository.save(mapToAcademicProgramRequest(academicProgramRequest));
		school.getAList().add(academicProgram);//many academic program to one school
		academicProgram.setSchool(school);

		schoolRepository.save(school);
		academicProgramRepository.save(academicProgram );

		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("AcademicProgram saved successfully");
		responseStructure.setData(mapToAcademicProgramResponse(academicProgram));

		return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(responseStructure,HttpStatus.CREATED);
	}
	/*====================================================== findAcademicProgram===============================================================*/
	@Override
	public ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> findAcademicProgram(int schoolId)
	{
		schoolRepository.findById(schoolId)
		.orElseThrow(()-> new SchoolObjectNotFoundException("school not found"));
      //AcademicProgramResponse does not accept list of programs(set of programs) so we pass the data one by one
		List<AcademicProgram> findAll = academicProgramRepository.findAll();
		List<AcademicProgramResponse> collect = findAll.stream()
				.map(u->mapToAcademicProgramResponse(u))
				.collect(Collectors.toList());

		if(findAll.isEmpty())
		{
			ListResponseStructure.setStatus(HttpStatus.FOUND.value());
			ListResponseStructure.setMessage("AcademicProgram is empty");
			ListResponseStructure.setData(collect);
		}

		ListResponseStructure.setStatus(HttpStatus.FOUND.value());
		ListResponseStructure.setMessage("AcademicProgram found successfully");
		ListResponseStructure.setData(collect);

		return new ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>>(ListResponseStructure,HttpStatus.FOUND);
	}
}
