package com.danit.erp.service.dictionary.status;

import com.danit.erp.domain.dictionary.status.SessionsStatus;
import com.danit.erp.repository.dictionary.status.SessionsStatusRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionsStatusService implements BaseService<SessionsStatus> {
  private final SessionsStatusRepository sessionsStatusRepository;

  @Override
  public List<SessionsStatus> findAll() {
    return sessionsStatusRepository.findByDeletedFalse();
  }

  @Override
  public List<SessionsStatus> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public SessionsStatus findById(Long userId) {
    return sessionsStatusRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку
  }


  @Override
  public SessionsStatus create(SessionsStatus obj) {
    SessionsStatus sessionsStatus = SessionsStatus.builder().status(obj.getStatus()).build();
    return sessionsStatusRepository.save(sessionsStatus);
  }

  @Override
  public void update(SessionsStatus obj) {
    SessionsStatus findSessionsStatus =
      sessionsStatusRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new Error());

    SessionsStatus groupStatus =
      SessionsStatus.builder().id(findSessionsStatus.getId()).status(obj.getStatus()).build();
    sessionsStatusRepository.save(groupStatus);
  }

  @Override
  public void delete(Long userId) {
    SessionsStatus findSessionsStatus =
      sessionsStatusRepository.findById(userId).orElseThrow(() -> new Error());

    //TODO зробити помилку
    findSessionsStatus.setDeleted(true);
    sessionsStatusRepository.save(findSessionsStatus);

  }
}
