package com.danit.erp.domain.dictionary;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.personalcard.PersonalCard;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contracts_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractStatus extends BaseEntity {
  private String status;

}

