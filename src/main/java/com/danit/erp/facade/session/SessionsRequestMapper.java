package com.danit.erp.facade.session;

import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.domain.dictionary.Sessions;
import com.danit.erp.domain.dictionary.status.SessionsStatus;
import com.danit.erp.dto.sessions.SessionsRequest;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.repository.dictionary.ProgramRepository;
import com.danit.erp.repository.dictionary.status.SessionsStatusRepository;
import org.springframework.stereotype.Service;

@Service

public class SessionsRequestMapper extends GeneralFacade<Sessions, SessionsRequest> {
  private final ProgramRepository programRepository;
  private final SessionsStatusRepository sessionsStatusRepository;

  public SessionsRequestMapper(ProgramRepository programRepository,
                               SessionsStatusRepository sessionsStatusRepository) {
    super(Sessions.class, SessionsRequest.class);


    this.programRepository = programRepository;
    this.sessionsStatusRepository = sessionsStatusRepository;
  }

  @Override
  protected void decorateDto(SessionsRequest dto, Sessions entity) {

    super.decorateDto(dto, entity);
  }

  @Override
  protected void decorateEntity(Sessions entity, SessionsRequest dto) {
    Program byProgram =
      programRepository.findByProgram(dto.getProgramName()).orElseThrow(()->new Error());
    SessionsStatus sessionsStatus =
      sessionsStatusRepository.findByStatus(dto.getSessionsStatus()).orElseThrow(() -> new Error());
    entity.setProgram(byProgram);
    entity.setSessionsStatus(sessionsStatus);

    super.decorateEntity(entity, dto);
  }
}