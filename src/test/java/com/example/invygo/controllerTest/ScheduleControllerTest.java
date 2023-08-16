package com.example.invygo.controllerTest;

import com.example.invygo.controller.ScheduleController;
import com.example.invygo.dto.ScheduleRequest;
import com.example.invygo.entity.Schedule;
import com.example.invygo.exception.ScheduleNotFoundException;
import com.example.invygo.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleControllerTest {

    @InjectMocks
    private ScheduleController scheduleController;

    @Mock
    private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddSchedule() {
        ScheduleRequest scheduleRequest = new ScheduleRequest(/* Initialize schedule request data */);
        Schedule savedSchedule = new Schedule(/* Initialize saved schedule data */);

        when(scheduleService.saveSchedule(scheduleRequest)).thenReturn(savedSchedule);

        ResponseEntity<Schedule> response = scheduleController.addSchedule(scheduleRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedSchedule, response.getBody());

        verify(scheduleService, times(1)).saveSchedule(scheduleRequest);
    }

    @Test
    void testGetAllSchedule() {
        List<Schedule> scheduleList = new ArrayList<>(); /* Initialize schedule list */

        when(scheduleService.getAllSchedule()).thenReturn(scheduleList);

        ResponseEntity<List<Schedule>> response = scheduleController.getAllSchedule();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(scheduleList, response.getBody());

        verify(scheduleService, times(1)).getAllSchedule();
    }

    @Test
    void testUpdateSchedule() throws ScheduleNotFoundException {
        int scheduleId = 1;
        ScheduleRequest scheduleRequest = new ScheduleRequest(/* Initialize updated schedule data */);
        Schedule updatedSchedule = new Schedule(/* Initialize updated schedule data */);

        when(scheduleService.updateSchedule(scheduleId, scheduleRequest)).thenReturn(updatedSchedule);

        ResponseEntity<Schedule> response = scheduleController.updateSchedule(scheduleId, scheduleRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSchedule, response.getBody());

        verify(scheduleService, times(1)).updateSchedule(scheduleId, scheduleRequest);
    }

    @Test
    void testGetSchedulesByUsername() throws ScheduleNotFoundException {
        String username = "exampleUser";
        List<Schedule> scheduleList = new ArrayList<>(); /* Initialize schedule list for the user */

        when(scheduleService.getScheduleByName(username)).thenReturn(scheduleList);

        List<Schedule> response = scheduleController.getSchedulesByUsername(username);

        assertEquals(scheduleList, response);

        verify(scheduleService, times(1)).getScheduleByName(username);
    }

    @Test
    void testDeleteSchedule() throws ScheduleNotFoundException {
        int scheduleId = 1;
        String expectedResponse = "Schedule deleted successfully";

        when(scheduleService.deleteSchedule(scheduleId)).thenReturn(expectedResponse);

        String response = scheduleController.deleteSchedule(scheduleId);

        assertEquals(expectedResponse, response);

        verify(scheduleService, times(1)).deleteSchedule(scheduleId);
    }
}

