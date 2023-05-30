package com.danit.erp.domain.dictionary;

import com.danit.erp.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "legal_entities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LegalEntity extends BaseEntity {
private String legalEntity;
private String  idCode;
private String  legalAddress;
private String  IBAN;
private String bank;
private String  representedBy;
private String statuteEntity;

}
