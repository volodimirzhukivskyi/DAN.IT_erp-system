package com.danit.erp.domain.dictionary;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.dictionary.status.SessionsStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Sessions extends BaseEntity {
  private String sessionsTopic;
  @ManyToOne
  private SessionsStatus sessionsStatus;
  @ManyToOne
  private Program program;
  private Integer plannedDuration;
  private String materialsLink;
  private boolean deleted;
}
