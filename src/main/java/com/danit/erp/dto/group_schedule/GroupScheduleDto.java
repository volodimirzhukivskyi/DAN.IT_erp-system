package com.danit.erp.dto.group_schedule;


import lombok.Data;

@Data
public class GroupScheduleDto {
  private Long id;

  private String groupName;
  private String dayOfWeek;

  private String startTime;
  private String endTime;


}
