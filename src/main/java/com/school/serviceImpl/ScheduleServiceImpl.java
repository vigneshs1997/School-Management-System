package com.school.serviceImpl;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.Repository.ScheduleRepository;
import com.school.Repository.SchoolRepository;
import com.school.entity.Schedule;
import com.school.entity.School;
import com.school.exception.ScheduleAlreadyExistException;
import com.school.exception.ScheduleObjectNotFoundException;
import com.school.exception.SchoolObjectNotFoundException;
import com.school.requestdto.ScheduleRequest;
import com.school.responsedto.ScheduleResponse;
import com.school.service.ScheduleService;
import com.school.util.ResponseStructure;
// class implemets interface
@Service
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private ResponseStructure<ScheduleResponse> responseStructure;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private SchoolRepository schoolRepository;
/*========================================================================MAPPING===========================================================================*/
	private Schedule mapToScheduleRequest(ScheduleRequest scheduleRequest)
	{

		System.out.println("Hello");
		return Schedule.builder() //Builder is used to build schduleRequest
				.opensAt(scheduleRequest.getOpensAt())
				.closesAt(scheduleRequest.getClosesAt())
				.classHoursPerDay(scheduleRequest.getClassHoursPerDay())
				.classHourLengthInMinutes(Duration.ofMinutes(scheduleRequest.getClassHourLengthInMinutes()))
				.breakTime(scheduleRequest.getBreakTime())
				.breakLengthInMinutes(Duration.ofMinutes(scheduleRequest.getBreakLengthInMinutes()))
				.lunchTime(scheduleRequest.getLunchTime())
				.lunchLengthInMinutes(Duration.ofMinutes(scheduleRequest.getLunchLengthInMinutes()))
				.build();
	}

	private ScheduleResponse mapToScheduleResponse(Schedule schedule)
	{
		return ScheduleResponse.builder()//Builder is used to build schduleResponse
				.scheduleId(schedule.getScheduleId())
				.opensAt(schedule.getOpensAt())
				.closesAt(schedule.getClosesAt())
				.classHoursPerDay(schedule.getClassHoursPerDay())
				.classHourLengthInMinutes((int)(Duration.ofMinutes(schedule.getClassHourLengthInMinutes().toMinutes()).toMinutes()))
				.breakTime(schedule.getBreakTime())
				.breakLengthInMinutes((int)(Duration.ofMinutes(schedule.getBreakLengthInMinutes().toMinutes()).toMinutes()))
				.lunchTime(schedule.getLunchTime())
				.lunchLengthInMinutes((int)(Duration.ofMinutes(schedule.getLunchLengthInMinutes().toMinutes()).toMinutes()))
				.build();
	}

/*====================================================================saveSchedule============================================================*/
	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> saveSchedule(int schoolId,
			ScheduleRequest scheduleRequest) 
	{
		//if id is there are not
		School school = schoolRepository.findById(schoolId)
				.orElseThrow(()-> new SchoolObjectNotFoundException("school not found"));
        //if there is any schedule data or not
		if(school.getSchedule()==null)
		{

			System.out.println(scheduleRequest.getOpensAt());
			Schedule schedule = scheduleRepository.save(mapToScheduleRequest(scheduleRequest));
			school.setSchedule(schedule);
			schoolRepository.save(school);

			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("schedule saved successfully");
			responseStructure.setData(mapToScheduleResponse(schedule));


			return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure,HttpStatus.CREATED);

		}
		else
		{
			throw new ScheduleAlreadyExistException("schedule already existed");
		}

	}
/*====================================================================findSchedule============================================================*/
	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(int schoolId) 
	{
		School school = schoolRepository.findById(schoolId)
				.orElseThrow(()->new SchoolObjectNotFoundException("school not found"));

		Schedule schedule = school.getSchedule();
		int scheduleId = schedule.getScheduleId();

		Optional<Schedule> findById = scheduleRepository.findById(scheduleId );
		schedule= findById.get();

		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("shedule found successfully");
		responseStructure.setData(mapToScheduleResponse(schedule));



		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure,HttpStatus.FOUND);

	}
/*====================================================================updateSchedule============================================================*/
	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> updateSchedule(int scheduleId,
			ScheduleRequest scheduleRequest) {

		Schedule schedule = scheduleRepository.findById(scheduleId)
				.map(u->{
					Schedule mapToScheduleRequest = mapToScheduleRequest(scheduleRequest);
					mapToScheduleRequest.setScheduleId(scheduleId);
					return scheduleRepository.save( mapToScheduleRequest );
				})
				.orElseThrow(()-> new ScheduleObjectNotFoundException("shedule not found") );



		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("shedule updated successfully");
		responseStructure.setData(mapToScheduleResponse(schedule));


		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure,HttpStatus.OK);
	}

}
