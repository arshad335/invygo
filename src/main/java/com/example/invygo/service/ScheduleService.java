package com.example.invygo.service;

import com.example.invygo.dto.ScheduleRequest;
import com.example.invygo.entity.Schedule;
import com.example.invygo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository repository;

    public Schedule saveSchedule(ScheduleRequest scheduleRequest) {
        Schedule schedule = Schedule.build(0, scheduleRequest.getWorkDate(), scheduleRequest.getUserName(), scheduleRequest.getShiftLength());
        return repository.save(schedule);
    }


    public List<Schedule> getAllSchedule() {
        return repository.findAll();
    }


/*    public User getUser(int id) throws UserNotFoundException {
        User user= repository.findByUserId(id);
        if(user!=null){
            return user;
        }else{
            throw new UserNotFoundException("user not found with id : "+id);
        }
    }*/

}
