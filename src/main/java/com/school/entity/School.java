package com.school.entity;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



//@Data=>(It has all of the stereo type entities)
@Getter
@Setter
@Entity
@Builder //using for mapping
@NoArgsConstructor
@AllArgsConstructor
public class School {
	@Id //=> It is primary key field
	@GeneratedValue(strategy = GenerationType.AUTO) //=>the database should automatically generate the values for this field.
	
	private int schoolId;
	private String schoolName;
	private long contactNo;
	private String emailId;
	private String address;
	
	@OneToOne 
	private Schedule schedule;
	
	@OneToMany(mappedBy = "school")
	private List<AcademicProgram>aList;
	 
}
