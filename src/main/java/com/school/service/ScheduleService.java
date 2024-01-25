package com.school.service;

import org.springframework.http.ResponseEntity;

import com.school.requestdto.ScheduleRequest;
import com.school.responsedto.ScheduleResponse;
import com.school.util.ResponseStructure;

public interface ScheduleService {

	ResponseEntity<ResponseStructure<ScheduleResponse>> saveSchedule(int schoolId,ScheduleRequest scheduleRequest);
	ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(int schoolId);
	ResponseEntity<ResponseStructure<ScheduleResponse>> updateSchedule(int scheduleId, ScheduleRequest scheduleRequest);
}
