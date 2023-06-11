package com.danit.erp.domain.dictionary;
import com.danit.erp.domain.group_schedule.GroupSchedule;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import com.danit.erp.domain.BaseEntity;
import java.time.LocalDateTime;
@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Group extends BaseEntity {
  private String groupName;
  private LocalDateTime startDate;
  private boolean deleted;
  @OneToMany(mappedBy = "group")
  private Set<GroupSchedule> groupSchedules = new HashSet<>();
}
