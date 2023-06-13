package com.danit.erp.dto.sessions;

import lombok.Data;

@Data
public class SessionsResponse {
  private String sessionsTopic;
  private String sessionsStatus;
  private String program;
  private Integer plannedDuration;
  private String materialsLink;
}
