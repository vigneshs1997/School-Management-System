package com.school.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


//@Data=>(@Getter,@Setter and everything)
@Getter
@Setter
@Entity//=>creating Table based on Schedule class
public class Schedule {
 @Id //=> It is primary key field
 @GeneratedValue(strategy = GenerationType.IDENTITY) //=>the database should automatically generate the values for this field.
  private int scheduleId;
 
 private LocalTime opensAt;
 private LocalTime closesAt;
 int classHoursPerDay;
 private LocalTime classHourLength;
 private LocalTime breakTime;
 private LocalTime breakLength;
 private LocalTime lunchTime;
 private LocalTime lunchLength;
	
}
