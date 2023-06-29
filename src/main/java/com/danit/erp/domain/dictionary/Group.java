package com.danit.erp.domain.dictionary;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.group_schedule.GroupSchedule;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "groups_dictionary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Group extends BaseEntity<Integer> {
  private String groupName;
  private LocalDateTime startDate;
  private boolean deleted;
  @OneToMany(mappedBy = "group")
  private Set<GroupSchedule> groupSchedules = new HashSet<>();
}
