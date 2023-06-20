package com.danit.erp.service;

import com.danit.erp.domain.group_schedule.GroupSchedule;
import com.danit.erp.dto.group_schedule.GroupScheduleDto;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.facade.group_schedule.GroupScheduleMapper;
import com.danit.erp.repository.GroupScheduleRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupScheduleService implements BaseService<GroupScheduleDto> {
  private final GroupScheduleRepository groupScheduleRepository;
  private final GroupScheduleMapper groupScheduleMapper;

  @Override
  public List<GroupScheduleDto> findAll() {

    List<GroupSchedule> allGroupSchedule = groupScheduleRepository.findAll();
    return allGroupSchedule.stream().map(groupScheduleMapper::convertToDto)
      .collect(Collectors.toList());

  }


  @Override
  public GroupScheduleDto findById(Long userId) {
    GroupSchedule findGroupSchedule = groupScheduleRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("Розклад"));
    return groupScheduleMapper.convertToDto(findGroupSchedule);
  }

  @Override
  public void update(GroupScheduleDto obj) {
    GroupSchedule convertSchedule = groupScheduleMapper.convertToEntity(obj);
    System.out.println(convertSchedule);
    groupScheduleRepository.save(convertSchedule);
  }

  @Override
  public GroupScheduleDto create(GroupScheduleDto obj) {
    GroupSchedule convertSchedule = groupScheduleMapper.convertToEntity(obj);

    groupScheduleRepository.save(convertSchedule);

    return groupScheduleMapper.convertToDto(convertSchedule);
  }


  @Override
  public void delete(Long userId) {
    GroupSchedule groupSchedule = groupScheduleRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("Розклад"));

    groupScheduleRepository.delete(groupSchedule);
  }
}
