package com.danit.erp.facade.group_schedule;

import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.domain.group_schedule.GroupSchedule;
import com.danit.erp.dto.group_schedule.GroupScheduleDto;
import com.danit.erp.exception.name.CouldNotFindNameException;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.repository.dictionary.GroupRepository;
import com.danit.erp.utils.Helper;
import org.springframework.stereotype.Service;

@Service

public class GroupScheduleMapper extends GeneralFacade<GroupSchedule, GroupScheduleDto> {

  private final GroupRepository groupRepository;

  public GroupScheduleMapper(
    GroupRepository groupRepository) {
    super(GroupSchedule.class, GroupScheduleDto.class);

    this.groupRepository = groupRepository;
  }

  @Override
  protected void decorateDto(GroupScheduleDto dto, GroupSchedule entity) {
    dto.setGroupName(entity.getGroup().getGroupName());
    dto.setStartTime(Helper.convertDate(entity.getStartTime(), "HH:mm"));
    dto.setEndTime(Helper.convertDate(entity.getEndTime(), "HH:mm"));
    super.decorateDto(dto, entity);
  }

  @Override
  protected void decorateEntity(GroupSchedule entity, GroupScheduleDto dto) {
    Group findGroup = groupRepository.findByGroupName(dto.getGroupName())
      .orElseThrow(() -> new CouldNotFindNameException("Групи"));

    entity.setGroup(findGroup);
    entity.setStartTime(Helper.convertInLocalDate(dto.getStartTime()));
    entity.setEndTime(Helper.convertInLocalDate(dto.getEndTime()));

    super.decorateEntity(entity, dto);
  }
}