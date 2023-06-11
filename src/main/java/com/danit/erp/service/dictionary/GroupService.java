package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.repository.GroupScheduleRepository;
import com.danit.erp.repository.dictionary.GroupRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService implements BaseService<Group> {
  private final GroupRepository groupRepository;
  private final GroupScheduleRepository groupScheduleRepository;

  @Override
  public List<Group> findAll() {
    return groupRepository.findByDeletedFalse();
  }

  @Override
  public List<Group> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public Group findById(Long userId) {
    return groupRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку
  }


  @Override
  public Group create(Group obj) {
    Group group =
      Group.builder().groupName(obj.getGroupName()).startDate(obj.getStartDate()).build();
    return groupRepository.save(group);
  }

  @Override
  public void update(Group obj) {
    Group findGroup =
      groupRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new Error());

    Group group = Group.builder().id(findGroup.getId()).groupName(obj.getGroupName())
      .startDate(obj.getStartDate()).build();
    groupRepository.save(group);
  }

  @Override
  public void delete(Long userId) {
    Group group = groupRepository.findById(userId).orElseThrow(() -> new Error());
    groupScheduleRepository.deleteAll(group.getGroupSchedules());
    //TODO зробити помилку
    group.setDeleted(true);
    groupRepository.save(group);

  }
}
