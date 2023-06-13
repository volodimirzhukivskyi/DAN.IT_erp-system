package com.danit.erp.facade.list.sessions_list;

import com.danit.erp.domain.dictionary.Sessions;
import com.danit.erp.domain.list.group_list.GroupList;
import com.danit.erp.domain.list.sessions_list.SessionsList;
import com.danit.erp.dto.list.sessions_list.SessionsListResponse;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.utils.Helper;
import org.springframework.stereotype.Service;

@Service
public class SessionsListResponseMapper extends GeneralFacade<SessionsList, SessionsListResponse> {


  public SessionsListResponseMapper() {
    super(SessionsList.class, SessionsListResponse.class);

  }

  @Override
  protected void decorateDto(SessionsListResponse dto, SessionsList entity) {
    Sessions sessions = entity.getSession();
    GroupList groupList = entity.getGroupList();
    dto.setProgram(groupList.getProgram().getProgram());
    dto.setGroup(groupList.getGroup().getGroupName());
    dto.setSessionsTopic(sessions.getSessionsTopic());
    dto.setTrainer(groupList.getTrainer().getFullName());
    dto.setActualDate(Helper.convertDate(entity.getActualDate(), "dd.MM.yyyy"));
    dto.setPlanDate(Helper.convertDate(entity.getPlanDate(), "dd.MM.yyyy"));
    super.decorateDto(dto, entity);
  }

  @Override
  public void decorateEntity(SessionsList entity, SessionsListResponse dto) {

    super.decorateEntity(entity, dto);
  }
}