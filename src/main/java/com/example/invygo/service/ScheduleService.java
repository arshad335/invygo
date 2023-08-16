package com.example.invygo.service;

import com.example.invygo.dto.ScheduleRequest;
import com.example.invygo.dto.UserRequest;
import com.example.invygo.entity.Schedule;
import com.example.invygo.entity.User;
import com.example.invygo.exception.ScheduleNotFoundException;
import com.example.invygo.exception.UserNotFoundException;
import com.example.invygo.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository repository;

    public Schedule saveSchedule(ScheduleRequest scheduleRequest) {
        Schedule schedule = Schedule.build(0, scheduleRequest.getWorkDate(), scheduleRequest.getUser(), scheduleRequest.getShiftLength());
        return repository.save(schedule);
    }


    public List<Schedule> getAllSchedule() {
        return repository.findAll();
    }

    public List<Schedule> getScheduleByName(String name) throws ScheduleNotFoundException {
        List<Schedule> schedules = repository.findByUser_Username(name);
        if(!schedules.isEmpty()){
            return schedules;
        }else{
            throw new ScheduleNotFoundException("No Schedule Assigned for : "+ name);
        }
    }

    public Schedule updateSchedule(int id, ScheduleRequest scheduleRequest) throws ScheduleNotFoundException{
        deleteSchedule(id);
        Schedule schedule = Schedule.build(id, scheduleRequest.getWorkDate(), scheduleRequest.getUser(), scheduleRequest.getShiftLength());
        return repository.save(schedule);
    }

    public String deleteSchedule(int id) throws ScheduleNotFoundException {

        Schedule schedule = repository.findScheduleById(id);

        if(schedule != null){
            repository.delete(schedule);
            return "Schedule Deleted with Id : " + id;
        }
        else {
            throw new ScheduleNotFoundException("Schedule Not Found with id :"+ id);
        }
    }

    @Transactional
    public void deleteOldData() {
        LocalDate cutoffDate = LocalDate.now().minusDays(365);
        repository.deleteOldSchedules(cutoffDate);
    }

}
