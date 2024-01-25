package com.school.requestdto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
//@Builder => not mapping to ScheduleRequest
public class ScheduleRequest {

	 private LocalTime opensAt;
	 private LocalTime closesAt;
	 private int classHoursPerDay;
	 private int classHourLengthInMinutes;
	 private LocalTime breakTime;
	 private int breakLengthInMinutes;
	 private LocalTime lunchTime;
	 private int lunchLengthInMinutes;
	
	}

