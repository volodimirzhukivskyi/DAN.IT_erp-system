package com.danit.erp.service.dictionary.status;

import com.danit.erp.domain.dictionary.status.GroupStatus;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.status.GroupStatusRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupStatusService implements BaseService<GroupStatus> {
  private final GroupStatusRepository groupStatusRepository;

  @Override
  public List<GroupStatus> findAll() {
    return groupStatusRepository.findByDeletedFalse();
  }

  @Override
  public List<GroupStatus> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public GroupStatus findById(Long userId) {
    return groupStatusRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new CouldNotFindException("Статусу  групи"));
  }


  @Override
  public GroupStatus create(GroupStatus obj) {
    GroupStatus groupStatus = GroupStatus.builder().status(obj.getStatus()).build();
    return groupStatusRepository.save(groupStatus);
  }

  @Override
  public void update(GroupStatus obj) {
    GroupStatus findGroupStatus =
      groupStatusRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new CouldNotFindException("Статусу  групи"));

    GroupStatus groupStatus =
      GroupStatus.builder().id(findGroupStatus.getId()).status(obj.getStatus()).build();
    groupStatusRepository.save(groupStatus);
  }

  @Override
  public void delete(Long userId) {
    GroupStatus findGroupStatus =
      groupStatusRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Статусу  групи"));

    findGroupStatus.setDeleted(true);
    groupStatusRepository.save(findGroupStatus);

  }
}
