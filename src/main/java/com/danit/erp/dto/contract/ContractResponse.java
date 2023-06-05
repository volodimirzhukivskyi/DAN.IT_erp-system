package com.danit.erp.dto.contract;

import java.time.LocalDateTime;
import lombok.Data;
@Data
public class ContractResponse {
  private Long id;
  private Long contractNo;
  private String contractDate;
  private Double contractValue;
  private String docLink;
  //TODO використати функцію для створення
  private String legalEntity;
  private String status;
  private String clientName;
  private String program;
  private Integer programHours;
  private String responsibleManager;
  private String coordinator;
  private String startDate;
  private String group;
}
