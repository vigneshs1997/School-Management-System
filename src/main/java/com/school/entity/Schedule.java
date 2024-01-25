package com.school.entity;

import java.time.Duration;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@Data=>(@Getter,@Setter and every Stereotype annotations)
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
 @Id //=> It is primary key field
 @GeneratedValue(strategy = GenerationType.IDENTITY) //=>the database should automatically generate the values for this field.
 private int scheduleId;
 private LocalTime opensAt;
 private LocalTime closesAt;
 private int classHoursPerDay;
 private Duration classHourLengthInMinutes;
 private LocalTime breakTime;
 private Duration breakLengthInMinutes;
 private LocalTime lunchTime;
 private Duration lunchLengthInMinutes;
	
}
