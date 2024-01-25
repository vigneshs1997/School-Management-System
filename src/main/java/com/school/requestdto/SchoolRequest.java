package com.school.requestdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Builder =>no mapping to SchoolRequest
public class SchoolRequest {
	private String schoolName;
	private long contactNo;
	private String emailId;
	private String address;
}
