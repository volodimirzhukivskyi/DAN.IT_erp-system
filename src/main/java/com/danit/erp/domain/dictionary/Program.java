package com.danit.erp.domain.dictionary;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import com.danit.erp.domain.BaseEntity;
@Entity
@Table(name = "programs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Program extends BaseEntity {
  private String program;
  private Integer programHours;
  private boolean deleted;
}
