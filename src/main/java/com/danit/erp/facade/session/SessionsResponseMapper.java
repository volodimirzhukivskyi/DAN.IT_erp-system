package com.danit.erp.facade.session;

import com.danit.erp.domain.dictionary.Sessions;
import com.danit.erp.dto.sessions.SessionsResponse;
import com.danit.erp.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class SessionsResponseMapper extends GeneralFacade<Sessions, SessionsResponse> {


  public SessionsResponseMapper() {
    super(Sessions.class, SessionsResponse.class);

  }

  @Override
  protected void decorateDto(SessionsResponse dto, Sessions entity) {

    dto.setProgram(entity.getProgram().getProgram());
    dto.setSessionsStatus(entity.getSessionsStatus().getStatus());
    super.decorateDto(dto, entity);
  }

  @Override
  public void decorateEntity(Sessions entity, SessionsResponse dto) {

    super.decorateEntity(entity, dto);
  }
}