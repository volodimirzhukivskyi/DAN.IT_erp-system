package com.danit.erp.domain.dictionary;

import com.danit.erp.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "legal_entities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LegalEntity extends BaseEntity {
private String legalEntityName;

private String  idCode;
private String  legalAddress;
private String  IBAN;
private String bank;
private String  representedBy;
private String statuteEntity;
private boolean deleted;

}
