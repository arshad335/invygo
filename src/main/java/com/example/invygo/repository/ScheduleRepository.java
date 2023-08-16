package com.example.invygo.repository;
import com.example.invygo.entity.Schedule;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findScheduleById(int id);

    List<Schedule> findByUser_Username(String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM Schedule s WHERE s.workDate < :cutoffDate")
    void deleteOldSchedules(LocalDate cutoffDate);
}
