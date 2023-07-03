package com.danit.erp.domain.list.sessions_list;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.dictionary.Sessions;
import com.danit.erp.domain.list.group_list.GroupList;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sessions_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SessionsList extends BaseEntity<Long> {
  @ManyToOne
  private Sessions session;
  private LocalDateTime planDate;
  private LocalDateTime actualDate;
  private Integer sessionDuration;
  private Integer plannedDuration;
  @ManyToOne
  private GroupList groupList;


}
