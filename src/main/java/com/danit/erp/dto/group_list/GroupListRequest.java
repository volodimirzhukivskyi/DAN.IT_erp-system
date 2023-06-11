package com.danit.erp.dto.group_list;



import lombok.Data;

@Data
public class GroupListRequest {

  private String groupGraduationDate;

  private String groupName;
  private String programName;
  private String groupStatus;
  private String trainerName;
  private String mentorName;
  private String coordinatorName;

}
