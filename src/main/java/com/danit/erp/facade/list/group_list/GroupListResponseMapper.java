package com.danit.erp.facade.list.group_list;

import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.domain.list.group_list.GroupList;
import com.danit.erp.dto.list.group_list.GroupListResponse;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.utils.Helper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class GroupListResponseMapper extends GeneralFacade<GroupList, GroupListResponse> {


  public GroupListResponseMapper() {
    super(GroupList.class, GroupListResponse.class);

  }

  @Override
  protected void decorateDto(GroupListResponse dto, GroupList entity) {
    LocalDateTime groupGraduation = entity.getGroupGraduation();
    Group group = entity.getGroup();
    List<String> collect =
      entity.getGroup().getGroupSchedules().stream().map(el->el.getDayOfWeek().name())
        .collect(Collectors.toList());
    String result = String.join(", ",collect);

    dto.setGroupGraduation(Helper.convertDate(groupGraduation, "dd.MM.yyyy"));
    dto.setGroupName(group.getGroupName());
    dto.setMentor(entity.getMentor().getFullName());
    dto.setTrainer(entity.getTrainer().getFullName());
    dto.setCoordinator(entity.getCoordinator().getFullName());
    dto.setGroupStatus(entity.getGroupStatus().getStatus());
    dto.setProgram(entity.getProgram().getProgram());
    dto.setGroupSchedule(result);
    super.decorateDto(dto, entity);
  }

  @Override
  public void decorateEntity(GroupList entity, GroupListResponse dto) {


  }
}