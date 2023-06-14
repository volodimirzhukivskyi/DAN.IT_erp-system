package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Sessions;
import com.danit.erp.dto.sessions.SessionsRequest;
import com.danit.erp.dto.sessions.SessionsResponse;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.facade.session.SessionsRequestMapper;
import com.danit.erp.facade.session.SessionsResponseMapper;
import com.danit.erp.repository.dictionary.SessionsRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionsService implements BaseService<SessionsResponse> {
  private final SessionsRepository sessionsRepository;
  private final SessionsRequestMapper sessionsRequestMapper;
  private final SessionsResponseMapper sessionsResponseMapper;

  @Override
  public List<SessionsResponse> findAll() {
    return sessionsRepository.findAll().stream().map(sessionsResponseMapper::convertToDto).toList();
  }

  @Override
  public List<SessionsResponse> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public SessionsResponse findById(Long userId) {
    Sessions sessions = sessionsRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Сесії"));
    return sessionsResponseMapper.convertToDto(sessions);
  }

  @Override
  public void update(SessionsResponse obj) {

  }

  @Override
  public SessionsResponse create(SessionsResponse obj) {
    return null;
  }

  public SessionsResponse create(SessionsRequest obj) {
    Sessions convertToEntity = sessionsRequestMapper.convertToEntity(obj);
    Sessions save = sessionsRepository.save(convertToEntity);
    return sessionsResponseMapper.convertToDto(save);
  }


  public void update(SessionsRequest obj) {
    Sessions sessions = sessionsRequestMapper.convertToEntity(obj);
    Sessions findSessions =
      sessionsRepository.findByProgram(sessions.getProgram()).orElseThrow(() -> new CouldNotFindException("Сесії"));

    Sessions session =
      Sessions.builder().id(findSessions.getId()).sessionsStatus(sessions.getSessionsStatus())
        .plannedDuration(sessions.getPlannedDuration()).sessionsTopic(sessions.getSessionsTopic())
        .program(sessions.getProgram()).materialsLink(sessions.getMaterialsLink()).build();
    sessionsRepository.save(session);
  }

  @Override
  public void delete(Long userId) {
    Sessions session = sessionsRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Сесії"));
    session.setDeleted(true);
    sessionsRepository.delete(session);
  }
}
