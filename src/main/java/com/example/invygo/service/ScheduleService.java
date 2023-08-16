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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository repository;

    @Autowired
    private UserService userService;

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
    public Map<String, Float> getUserTotalShiftLengthsSorted(int days) {
        List<Schedule> allSchedules = repository.findAll();
        LocalDate enddate = LocalDate.now();
        LocalDate startDate = enddate.minusDays(days);

        Map<String, Float> userTotalShiftLengths = allSchedules.stream()
                .filter(date -> date.getWorkDate().isAfter(startDate) && date.getWorkDate().isBefore(enddate))
                .collect(Collectors.groupingBy(schedule -> schedule.getUser().getUsername(),
                        Collectors.summingDouble(Schedule::getShiftLength)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue()) // Sort by values (total shift lengths)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().floatValue(),
                        (e1, e2) -> e1, LinkedHashMap::new)); // Maintain order in a LinkedHashMap

        return userTotalShiftLengths;
    }
    @Transactional
    public void deleteOldData() {
        LocalDate cutoffDate = LocalDate.now().minusDays(365);
        repository.deleteOldSchedules(cutoffDate);
    }

}
