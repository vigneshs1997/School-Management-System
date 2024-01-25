package com.school.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.Schedule;

//interface extends interface
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

}
