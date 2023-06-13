package com.danit.erp.dto.sessions;


import lombok.Data;

@Data
public class SessionsRequest {
  private String sessionsTopic;
  private String sessionsStatus;
  private String programName;
  private Integer plannedDuration;
  private String materialsLink;

}
