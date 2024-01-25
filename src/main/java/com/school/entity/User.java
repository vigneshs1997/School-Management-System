package com.school.entity;


import java.util.List;

import org.springframework.stereotype.Component;

import com.school.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Builder//It is used to create builder methods with is used to create user object(builder is a interface)
//@Component
@NoArgsConstructor
@AllArgsConstructor
public class User {
//GenerationType is also enum which contains IDENTITY and also for calling every thing easily
 @GeneratedValue(strategy = GenerationType.IDENTITY)//automaticaly taking count
 @Id
 private int userId;
 @Column(unique = true) //It is unique name given like in GitHub
 private String userName;
 private String password;
 private String firstName;
 private String lastName;
 private long contactNo;
 @Column(unique = true) //It should be unique
 private String email;
 //because the datatype is enum
 private UserRole userRole;
 //It is used for school operation
 private boolean isDeleted;
 
 @ManyToOne //school and user relation is given in serviceImplimentation of school serviceImplimentation(where maping starts)
 private School school;
 
 @ManyToMany(mappedBy = "users") //colums of user and user Id are created in academicprogram table 
	private List<AcademicProgram>academicProgram;
 @ManyToOne
 private Subject subject;
public List<AcademicProgram> getListOfAcademicPrograms() {
	
	return null;
}
}
