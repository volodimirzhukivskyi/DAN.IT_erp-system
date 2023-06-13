package com.danit.erp.facade.list.sessions_list;

import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.domain.dictionary.Sessions;
import com.danit.erp.domain.list.group_list.GroupList;
import com.danit.erp.domain.list.sessions_list.SessionsList;
import com.danit.erp.dto.list.sessions_list.SessionsListRequest;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.repository.list.GroupListRepository;
import com.danit.erp.repository.dictionary.GroupRepository;
import com.danit.erp.repository.dictionary.ProgramRepository;
import com.danit.erp.repository.dictionary.SessionsRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service

public class SessionsListRequestMapper extends GeneralFacade<SessionsList, SessionsListRequest> {
  private final SessionsRepository sessionsRepository;
  private final ProgramRepository programRepository;
  private final GroupRepository groupRepository;
  private final GroupListRepository groupListRepository;

  public SessionsListRequestMapper(
    SessionsRepository sessionsRepository, ProgramRepository programRepository,
    GroupRepository groupRepository, GroupListRepository groupListRepository) {
    super(SessionsList.class, SessionsListRequest.class);


    this.sessionsRepository = sessionsRepository;
    this.programRepository = programRepository;
    this.groupRepository = groupRepository;
    this.groupListRepository = groupListRepository;
  }

  @Override
  protected void decorateDto(SessionsListRequest dto, SessionsList entity) {

    super.decorateDto(dto, entity);
  }

  @Override
  protected void decorateEntity(SessionsList entity, SessionsListRequest dto) {
    Program program =
      programRepository.findByProgram(dto.getProgram()).orElseThrow(() -> new Error());
    Sessions sessions = sessionsRepository.findByProgram(program).orElseThrow(() -> new Error());
    Group group = groupRepository.findByGroupName(dto.getGroup()).orElseThrow(() -> new Error());
    GroupList groupList = groupListRepository.findByGroup(group).orElseThrow(() -> new Error());

    entity.setSession(sessions);
    entity.setGroupList(groupList);
    entity.setActualDate(LocalDateTime.parse(dto.getActualDate()));
    entity.setPlanDate(LocalDateTime.parse(dto.getPlanDate()));
    super.decorateEntity(entity, dto);
  }
}