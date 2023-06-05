package com.danit.erp.dto.contract;

import com.danit.erp.domain.dictionary.ContractStatus;
import com.danit.erp.domain.dictionary.Coordinator;
import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.domain.dictionary.LegalEntity;
import com.danit.erp.domain.dictionary.Manager;
import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.domain.personalcard.PersonalCard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ContractRequest {
  private Long contractNo;
  private LocalDateTime contractDate;
  private Double contractValue;
  private String docLink;
  private String legalEntityCode;
  private String personalCardCode;
  private String coordinatorFullName;
  private String responsibleManagerFullName;
  private String contractStatus;
  private String programName;
  private String groupName;
  private String paymentMethod;
}
