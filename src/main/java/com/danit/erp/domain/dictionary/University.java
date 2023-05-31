package com.danit.erp.domain.dictionary;

import com.danit.erp.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "universities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class University extends BaseEntity {
  private String name;
}
