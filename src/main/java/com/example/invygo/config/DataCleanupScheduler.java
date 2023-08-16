package com.example.invygo.config;

import com.example.invygo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataCleanupScheduler {

    @Autowired
    private ScheduleService scheduleService;

    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
    public void runDataCleanup() {
        scheduleService.deleteOldData();
    }

}
