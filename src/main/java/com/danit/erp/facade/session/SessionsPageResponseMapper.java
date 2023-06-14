package com.danit.erp.facade.session;

import com.danit.erp.domain.dictionary.Sessions;
import com.danit.erp.dto.sessions.PageSessionsResponse;
import com.danit.erp.dto.sessions.SessionsResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SessionsPageResponseMapper {
  private final SessionsResponseMapper sessionsResponseMapper;

  public SessionsPageResponseMapper(SessionsResponseMapper sessionsResponseMapper) {
    this.sessionsResponseMapper = sessionsResponseMapper;
  }

  public PageSessionsResponse convertToDto(Page<Sessions> entity) {
    PageSessionsResponse dto = new PageSessionsResponse();
    dto.setTotalPages(entity.getTotalPages());
    dto.setTotalElements(entity.getTotalElements());
    List<Sessions> sessions = entity.getContent();
    if (sessions.size() > 0) {
      List<SessionsResponse> sessionsResponses =
        sessions.stream().map(sessionsResponseMapper::convertToDto).toList();
      dto.setContent(sessionsResponses);
    }
    return dto;
  }


}