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

import com.school.requestdto.SchoolRequest;
import com.school.responsedto.SchoolResponse;
import com.school.service.SchoolService;
import com.school.util.ResponseStructure;

@RestController
public class SchoolController {
	@Autowired
	private SchoolService schoolService;

	//@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('STUDENT')")//ADMIN only can delete everything
	@PreAuthorize("hasAuthority('ADMIN')")//ADMIN only can delete everything
	@PostMapping("/users/schools")  // here we need one to many /*for taking Intiger               ,for taking object                      */
	public ResponseEntity<ResponseStructure<SchoolResponse>> saveSchool(@RequestBody  SchoolRequest school)
	{
		return schoolService.saveSchool(school);
	}

	@PutMapping("/schools/{schoolId}")                                   /*for Intiger               ,for object                     */
	public ResponseEntity<ResponseStructure<SchoolResponse>> updateSchool(@PathVariable int schoolId,@RequestBody SchoolRequest school)
	{
		return schoolService.updateSchool(schoolId,school);
	}

	@GetMapping("/schools")
	public ResponseEntity<ResponseStructure<List<SchoolResponse>>> findAllSchool()
	{
		return schoolService.findAllSchool();
	}
	@DeleteMapping("/schools/{schoolId}")                                 /*for Intiger*/
	public ResponseEntity<ResponseStructure<SchoolResponse>> deleteSchool(@PathVariable int schoolId)
	{
		return schoolService.deleteSchool(schoolId);
	}
	@GetMapping("/schools/{schoolId}")                                      /*for Intiger*/
	public ResponseEntity<ResponseStructure<SchoolResponse>> findSchoolById(@PathVariable int schoolId)
	{
		return schoolService.findSchoolById(schoolId);
	}
}
