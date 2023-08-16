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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/schedule")
@EnableMethodSecurity
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Schedule> addSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest) {
        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Schedule>> getAllSchedule(){
        return ResponseEntity.ok(scheduleService.getAllSchedule());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable("id") int id, @RequestBody @Valid ScheduleRequest scheduleRequest) throws ScheduleNotFoundException {

        Schedule schedule = scheduleService.updateSchedule(id, scheduleRequest);
        return new ResponseEntity<Schedule>(schedule, HttpStatus.OK);
    }
    @GetMapping("/name")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #username == principal.username)")
    public List<Schedule> getSchedulesByUsername(@RequestParam String username, Principal principal) throws ScheduleNotFoundException {
        return scheduleService.getScheduleByName(username);
    }

    @GetMapping("/getAll/{days}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Float>> getAllScheduleOrdered(@PathVariable int days){
        return ResponseEntity.ok(scheduleService.getUserTotalShiftLengthsSorted(days));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSchedule(@PathVariable int id) throws ScheduleNotFoundException {
        return scheduleService.deleteSchedule(id);
    }

}