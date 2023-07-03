package com.danit.erp.service;

import com.danit.erp.domain.group_schedule.GroupSchedule;
import com.danit.erp.dto.group_schedule.GroupScheduleDto;
import com.danit.erp.exception.id.CouldNotFindException;
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
public class GroupScheduleService implements BaseService<GroupScheduleDto,Integer> {
  private final GroupScheduleRepository groupScheduleRepository;
  private final GroupScheduleMapper groupScheduleMapper;

  @Override
  public List<GroupScheduleDto> findAll() {

    List<GroupSchedule> allGroupSchedule = groupScheduleRepository.findAll();
    return allGroupSchedule.stream().map(groupScheduleMapper::convertToDto)
      .collect(Collectors.toList());

  }


  @Override
  public GroupScheduleDto findById(Integer userId) {
    GroupSchedule findGroupSchedule = groupScheduleRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("The schedule"));
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
  public void delete(Integer userId) {
    GroupSchedule groupSchedule = groupScheduleRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("The schedule"));

    groupScheduleRepository.delete(groupSchedule);
  }
}
