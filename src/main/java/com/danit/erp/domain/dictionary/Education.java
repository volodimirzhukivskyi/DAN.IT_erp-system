package com.danit.erp.domain.dictionary;

import com.danit.erp.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "education_specializations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Education extends BaseEntity {
  private String specialization;
  private boolean deleted;

}
