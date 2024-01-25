package com.school.responsedto;

import java.time.Duration;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder //=>Mapping to ScheduleResponse
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {

	 private int scheduleId;
	 private LocalTime opensAt;
	 private LocalTime closesAt;
	 private int classHoursPerDay;
	 private int classHourLengthInMinutes;
	 private LocalTime breakTime;
	 private int breakLengthInMinutes;
	 private LocalTime lunchTime;
	 private int lunchLengthInMinutes;
}
