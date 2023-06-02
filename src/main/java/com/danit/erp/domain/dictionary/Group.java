package com.danit.erp.domain.dictionary;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}
