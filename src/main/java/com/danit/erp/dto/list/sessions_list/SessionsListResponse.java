package com.danit.erp.dto.list.sessions_list;

import lombok.Data;

@Data
public class SessionsListResponse {
  private String sessionsTopic;
  private String program;
  private String group;
  private String trainer;
  private Integer plannedDuration;
  private String planDate;
  private String actualDate;
  private Integer sessionDuration;
}
