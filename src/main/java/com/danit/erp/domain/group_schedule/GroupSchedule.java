package com.danit.erp.domain.group_schedule;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.dictionary.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "groups_schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GroupSchedule extends BaseEntity<Integer> {


  @ManyToOne
  @JoinColumn(name = "group_id")
  @JsonIgnore
  private Group group;
  @Enumerated(EnumType.STRING)
  private DayOfWeek dayOfWeek;

  private LocalDateTime startTime;
  private LocalDateTime endTime;


}
