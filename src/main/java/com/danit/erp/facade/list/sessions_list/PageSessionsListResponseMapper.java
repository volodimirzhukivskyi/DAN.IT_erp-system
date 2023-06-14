package com.danit.erp.facade.list.sessions_list;

import com.danit.erp.domain.list.sessions_list.SessionsList;
import com.danit.erp.dto.list.sessions_list.PageSessionsListResponse;
import com.danit.erp.dto.list.sessions_list.SessionsListResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PageSessionsListResponseMapper {
  private final SessionsListResponseMapper sessionsListResponseMapper;

  public PageSessionsListResponseMapper(SessionsListResponseMapper sessionsListResponseMapper) {
    this.sessionsListResponseMapper = sessionsListResponseMapper;
  }

  public PageSessionsListResponse convertToDto(Page<SessionsList> entity) {
    PageSessionsListResponse dto = new PageSessionsListResponse();

    dto.setTotalPages(entity.getTotalPages());
    dto.setTotalElements(entity.getTotalElements());
    List<SessionsList> sessions = entity.getContent();
    if (sessions.size() > 0) {
      List<SessionsListResponse> sessionsResponses =
        sessions.stream().map(sessionsListResponseMapper::convertToDto).toList();
      dto.setContent(sessionsResponses);
    }
    return dto;
  }


}