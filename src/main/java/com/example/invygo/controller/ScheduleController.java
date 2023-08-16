package com.example.invygo.controller;

import com.example.invygo.dto.ScheduleRequest;
import com.example.invygo.dto.UserRequest;
import com.example.invygo.entity.Schedule;
import com.example.invygo.entity.User;
import com.example.invygo.exception.ScheduleNotFoundException;
import com.example.invygo.exception.UserNotFoundException;
import com.example.invygo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/add")
    public ResponseEntity<Schedule> addSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest) {
        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Schedule>> getAllSchedule(){
        return ResponseEntity.ok(scheduleService.getAllSchedule());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable("id") int id, @RequestBody @Valid ScheduleRequest scheduleRequest) throws ScheduleNotFoundException {

        Schedule schedule = scheduleService.updateSchedule(id, scheduleRequest);
        return new ResponseEntity<Schedule>(schedule, HttpStatus.OK);
    }
    @GetMapping("/name")
    public List<Schedule> getSchedulesByUsername(@RequestParam String username) throws ScheduleNotFoundException {
        return scheduleService.getScheduleByName(username);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable int id) throws ScheduleNotFoundException {
        return scheduleService.deleteSchedule(id);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleByUserId(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(scheduleService.getScheduleByUserId());
    }*/
}