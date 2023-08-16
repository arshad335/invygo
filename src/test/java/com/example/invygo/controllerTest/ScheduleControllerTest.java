package com.example.invygo.controllerTest;

import com.example.invygo.controller.ScheduleController;
import com.example.invygo.dto.ScheduleRequest;
import com.example.invygo.entity.Schedule;
import com.example.invygo.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ScheduleControllerTest {

    @InjectMocks
    private ScheduleController scheduleController;

    @Mock
    private ScheduleService scheduleService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    /*@Test
    @WithMockUser(roles = "ADMIN")
    void testAddSchedule() throws Exception {
        ScheduleRequest scheduleRequest = new ScheduleRequest(*//* Initialize schedule request data *//*);
        Schedule savedSchedule = new Schedule(*//* Initialize saved schedule data *//*);

        when(scheduleService.saveSchedule(scheduleRequest)).thenReturn(savedSchedule);

        mockMvc.perform(post("/api/v1/schedule/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(scheduleRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        verify(scheduleService, times(1)).saveSchedule(scheduleRequest);
    }*/

    @Test
    void testGetAllSchedule() throws Exception {
        List<Schedule> scheduleList = new ArrayList<>(); /* Initialize schedule list */

        when(scheduleService.getAllSchedule()).thenReturn(scheduleList);

        mockMvc.perform(get("/api/v1/schedule/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(scheduleService, times(1)).getAllSchedule();
    }

    /*@Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateSchedule() throws Exception {
        int scheduleId = 1;
        ScheduleRequest scheduleRequest = new ScheduleRequest(*//* Initialize updated schedule data *//*);
        Schedule updatedSchedule = new Schedule(*//* Initialize updated schedule data *//*);

        when(scheduleService.updateSchedule(scheduleId, scheduleRequest)).thenReturn(updatedSchedule);

        mockMvc.perform(put("/api/v1/schedule/{id}", scheduleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(scheduleRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(scheduleId));

        verify(scheduleService, times(1)).updateSchedule(scheduleId, scheduleRequest);
    }*/

    @Test
    @WithMockUser(roles = "USER")
    void testGetSchedulesByUsernameAsUser() throws Exception {
        String username = "exampleUser";
        List<Schedule> scheduleList = new ArrayList<>(); /* Initialize schedule list for the user */

        when(scheduleService.getScheduleByName(username)).thenReturn(scheduleList);

        mockMvc.perform(get("/api/v1/schedule/name")
                        .param("username", username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(scheduleService, times(1)).getScheduleByName(username);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetAllScheduleOrderedAsAdmin() throws Exception {
        int days = 7;
        Map<String, Float> scheduleMap = new HashMap<>(); /* Initialize the schedule map */

        when(scheduleService.getUserTotalShiftLengthsSorted(days)).thenReturn(scheduleMap);

        mockMvc.perform(get("/api/v1/schedule/getAll/{days}", days))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap());

        verify(scheduleService, times(1)).getUserTotalShiftLengthsSorted(days);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteSchedule() throws Exception {
        int scheduleId = 1;

        when(scheduleService.deleteSchedule(scheduleId)).thenReturn("Schedule deleted successfully");

        mockMvc.perform(delete("/api/v1/schedule/delete/{id}", scheduleId))
                .andExpect(status().isOk())
                .andExpect(content().string("Schedule deleted successfully"));

        verify(scheduleService, times(1)).deleteSchedule(scheduleId);
    }

    // Helper method to convert objects to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
