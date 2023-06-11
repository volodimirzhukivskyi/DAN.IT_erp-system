package com.danit.erp.dto.group_list;

import lombok.Builder;
import lombok.Data;

@Data
public class GroupListResponse {
  private Long id;
  private String groupName;
  private String program;
  private  String groupGraduation;
  private String groupStatus;
  private String groupSchedule;
  private String trainer;
  private String mentor;
  private String coordinator;
}
