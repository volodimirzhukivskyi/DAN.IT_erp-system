package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.exception.id.CouldNotFindException;
import com.danit.erp.repository.GroupScheduleRepository;
import com.danit.erp.repository.dictionary.GroupRepository;
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
public class GroupService implements BaseService<Group,Integer> {
  private final GroupRepository groupRepository;
  private final GroupScheduleRepository groupScheduleRepository;

  @Override
  public List<Group> findAll() {
    return groupRepository.findByDeletedFalse();
  }

  @Override
  public Page<Group> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return groupRepository.findByDeletedFalse(pageable);
  }

  @Override
  public Group findById(Integer userId) {
    return groupRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("The groups"));
  }


  @Override
  public Group create(Group obj) {
    Group group =
      Group.builder().groupName(obj.getGroupName()).startDate(obj.getStartDate()).build();
    return groupRepository.save(group);
  }

  @Override
  public void update(Group obj) {
    Group findGroup = groupRepository.findByIdAndDeletedFalse(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("The groups"));

    Group group = Group.builder().id(findGroup.getId()).groupName(obj.getGroupName())
      .startDate(obj.getStartDate()).build();
    groupRepository.save(group);
  }

  @Override
  public void delete(Integer userId) {
    Group group =
      groupRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("The groups"));
    groupScheduleRepository.deleteAll(group.getGroupSchedules());
    group.setDeleted(true);
    groupRepository.save(group);

  }
}
