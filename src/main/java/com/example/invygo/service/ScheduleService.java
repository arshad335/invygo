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

    public Map<String, Float> orderUserNameByHrs(int days){



        Map<String, Float> result = new HashMap<>();

        LocalDate enddate = LocalDate.now();
        LocalDate startDate = enddate.minusDays(days);

        for(String user : map.keySet()){
            result.put(user, (float) map.get(user).stream().filter(date -> date.getWorkDate().isAfter(startDate) && date.getWorkDate().isBefore(enddate))
                    .map(scheduleV1 -> scheduleV1.getShiftLength()).mapToDouble(Float::floatValue).sum());
        }

        LinkedHashMap<String, Float> sortedHashMap = result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );

        return sortedHashMap;

    }

    @Transactional
    public void deleteOldData() {
        LocalDate cutoffDate = LocalDate.now().minusDays(365);
        repository.deleteOldSchedules(cutoffDate);
    }

}
