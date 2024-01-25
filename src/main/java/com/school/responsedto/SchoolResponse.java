package com.school.responsedto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder // mapping to SchoolResponse
public class SchoolResponse {
	private Integer schoolId;
	private String schoolName;
	private long contactNo;
	private String emailId;
	private String address;
}
