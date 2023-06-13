package com.danit.erp.dto.card.contract;

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
