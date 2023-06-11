package com.danit.erp.repository;

import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.domain.group_schedule.GroupSchedule;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupScheduleRepository extends JpaRepository<GroupSchedule,Long> {

  Optional<GroupSchedule> findByGroup(Group group);
}
