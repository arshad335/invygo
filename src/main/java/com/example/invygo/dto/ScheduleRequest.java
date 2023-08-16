package com.example.invygo.dto;

import com.example.invygo.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ScheduleRequest {
    @NotNull(message = "Date Cannot Be Null")
    private LocalDate workDate;
    @Valid
    private User user;
    private Float shiftLength;
}
