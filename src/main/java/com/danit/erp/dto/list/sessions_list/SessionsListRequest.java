package com.danit.erp.dto.list.sessions_list;


import lombok.Data;

@Data
public class SessionsListRequest {
  private String program;
  private String group;

  private String planDate;
  private String actualDate;
  private Integer plannedDuration;
  private Integer sessionDuration;

}
