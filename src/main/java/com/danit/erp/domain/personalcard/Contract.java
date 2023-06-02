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
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "contract_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Contract extends BaseEntity {
  @Column(name = "contract_no")
  private Long contractNo;
  private LocalDateTime contractDate;
  private Double contractValue;
  private String docLink;
  private String clientName;

  private String paymentMethod;

}
