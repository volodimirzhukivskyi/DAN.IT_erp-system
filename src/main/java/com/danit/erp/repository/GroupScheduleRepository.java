package com.danit.erp.repository;

import com.danit.erp.domain.group_schedule.GroupSchedule;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupScheduleRepository extends JpaRepository<GroupSchedule, Long> {

  Optional<GroupSchedule> findByDayOfWeek(String group);
}
