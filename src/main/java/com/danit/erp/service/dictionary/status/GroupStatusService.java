package com.danit.erp.service.dictionary.status;

import com.danit.erp.domain.dictionary.status.GroupStatus;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.status.GroupStatusRepository;
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
public class GroupStatusService implements BaseService<GroupStatus,Byte> {
  private final GroupStatusRepository groupStatusRepository;

  @Override
  public List<GroupStatus> findAll() {
    return groupStatusRepository.findByDeletedFalse();
  }

  @Override
  public Page<GroupStatus> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return groupStatusRepository.findByDeletedFalse(pageable);
  }

  @Override
  public GroupStatus findById(Byte userId) {
    return groupStatusRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("Статусу  групи"));
  }


  @Override
  public GroupStatus create(GroupStatus obj) {
    GroupStatus groupStatus = GroupStatus.builder().status(obj.getStatus()).build();
    return groupStatusRepository.save(groupStatus);
  }

  @Override
  public void update(GroupStatus obj) {
    GroupStatus findGroupStatus = groupStatusRepository.findByIdAndDeletedFalse(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("Статусу  групи"));

    GroupStatus groupStatus =
      GroupStatus.builder().id(findGroupStatus.getId()).status(obj.getStatus()).build();
    groupStatusRepository.save(groupStatus);
  }

  @Override
  public void delete(Byte userId) {
    GroupStatus findGroupStatus = groupStatusRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("Статусу  групи"));

    findGroupStatus.setDeleted(true);
    groupStatusRepository.save(findGroupStatus);

  }
}
