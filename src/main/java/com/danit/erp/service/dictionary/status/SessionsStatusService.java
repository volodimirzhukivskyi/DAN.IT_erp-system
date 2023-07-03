package com.danit.erp.service.dictionary.status;

import com.danit.erp.domain.dictionary.status.SessionsStatus;
import com.danit.erp.exception.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.status.SessionsStatusRepository;
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
public class SessionsStatusService implements BaseService<SessionsStatus,Byte> {
  private final SessionsStatusRepository sessionsStatusRepository;

  @Override
  public List<SessionsStatus> findAll() {
    return sessionsStatusRepository.findByDeletedFalse();
  }

  @Override
  public Page<SessionsStatus> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return sessionsStatusRepository.findByDeletedFalse(pageable);
  }

  @Override
  public SessionsStatus findById(Byte userId) {
    return sessionsStatusRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("The session status"));
  }


  @Override
  public SessionsStatus create(SessionsStatus obj) {
    SessionsStatus sessionsStatus = SessionsStatus.builder().status(obj.getStatus()).build();
    return sessionsStatusRepository.save(sessionsStatus);
  }

  @Override
  public void update(SessionsStatus obj) {
    SessionsStatus findSessionsStatus =
      sessionsStatusRepository.findByIdAndDeletedFalse(obj.getId())
        .orElseThrow(() -> new CouldNotFindException("The session status"));

    SessionsStatus groupStatus =
      SessionsStatus.builder().id(findSessionsStatus.getId()).status(obj.getStatus()).build();
    sessionsStatusRepository.save(groupStatus);
  }

  @Override
  public void delete(Byte userId) {
    SessionsStatus findSessionsStatus = sessionsStatusRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("The session status"));

    findSessionsStatus.setDeleted(true);
    sessionsStatusRepository.save(findSessionsStatus);

  }
}
