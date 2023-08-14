package com.example.invygo.dto;

import com.example.invygo.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ScheduleRequest {
    @NotNull(message = "Date Cannot Be Null")
    private Date workDate;
    @Valid
    private User userName;
    private Float shiftLength;
}
