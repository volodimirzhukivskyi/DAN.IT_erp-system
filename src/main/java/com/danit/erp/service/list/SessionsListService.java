package com.danit.erp.service.list;

import com.danit.erp.domain.list.sessions_list.SessionsList;
import com.danit.erp.dto.list.sessions_list.PageSessionsListResponse;
import com.danit.erp.dto.list.sessions_list.SessionsListRequest;
import com.danit.erp.dto.list.sessions_list.SessionsListResponse;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.facade.list.sessions_list.PageSessionsListResponseMapper;
import com.danit.erp.facade.list.sessions_list.SessionsListRequestMapper;
import com.danit.erp.facade.list.sessions_list.SessionsListResponseMapper;
import com.danit.erp.repository.list.SessionsListRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionsListService implements BaseService<SessionsListResponse,Long> {
  private final SessionsListRepository sessionsListRepository;

  private final SessionsListRequestMapper sessionsListRequestMapper;
  private final SessionsListResponseMapper sessionsListResponseMapper;
  private final PageSessionsListResponseMapper pageSessionsListResponseMapper;

  @Override
  public List<SessionsListResponse> findAll() {

    List<SessionsList> all = sessionsListRepository.findAll();
    return all.stream().map(sessionsListResponseMapper::convertToDto).toList();
  }


  public PageSessionsListResponse getAllPage(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    Page<SessionsList> all = sessionsListRepository.findAll(pageable);
    return pageSessionsListResponseMapper.convertToDto(all);
  }

  @Override
  public SessionsListResponse findById(Long userId) {
    SessionsList sessionsList = sessionsListRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("Список сесій"));
    return sessionsListResponseMapper.convertToDto(sessionsList);
  }

  @Override
  public void update(SessionsListResponse obj) {

  }

  @Override
  public SessionsListResponse create(SessionsListResponse obj) {
    return null;
  }

  public SessionsListResponse create(SessionsListRequest sessionsListRequest) {

    SessionsList obj = sessionsListRequestMapper.convertToEntity(sessionsListRequest);

    SessionsList save = sessionsListRepository.save(obj);
    return sessionsListResponseMapper.convertToDto(save);
  }

  public void update(SessionsListRequest SessionsListRequest) {

    SessionsList obj = sessionsListRequestMapper.convertToEntity(SessionsListRequest);
    SessionsList findSessionsList = sessionsListRepository.findById(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("Список сесій"));

    SessionsList sessionsList =
      SessionsList.builder().id(findSessionsList.getId()).groupList(findSessionsList.getGroupList())
        .session(findSessionsList.getSession())
        .sessionDuration(findSessionsList.getSessionDuration())
        .actualDate(findSessionsList.getActualDate()).planDate(findSessionsList.getPlanDate())
        .build();
    sessionsListRepository.save(sessionsList);
  }

  @Override
  public void delete(Long userId) {
    SessionsList sessionsList = sessionsListRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("Список сесій"));

    sessionsListRepository.delete(sessionsList);
  }
}
