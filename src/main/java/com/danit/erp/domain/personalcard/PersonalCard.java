package com.danit.erp.domain.personalcard;

import com.danit.erp.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personal_cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalCard extends BaseEntity {
  @Column(name = "contract_number")
  private Long contractNo;
  private LocalDateTime contractDate;
  private Double contractValue;

}
