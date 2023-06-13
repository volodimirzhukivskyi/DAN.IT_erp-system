package com.danit.erp.service.list;

import com.danit.erp.domain.list.group_list.GroupList;
import com.danit.erp.dto.list.group_list.GroupListRequest;
import com.danit.erp.dto.list.group_list.GroupListResponse;
import com.danit.erp.facade.list.group_list.GroupListRequestMapper;
import com.danit.erp.facade.list.group_list.GroupListResponseMapper;
import com.danit.erp.repository.list.GroupListRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupListService implements BaseService<GroupListResponse> {
  private final GroupListRepository groupListRepository;
  private final GroupListRequestMapper groupListRequestMapper;
  private final GroupListResponseMapper groupListResponseMapper;

  @Override
  public List<GroupListResponse> findAll() {
    List<GroupList> all = groupListRepository.findAll();
    return all.stream().map(groupListResponseMapper::convertToDto).toList();
  }

  @Override
  public List<GroupListResponse> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public GroupListResponse findById(Long userId) {
    GroupList groupList = groupListRepository.findById(userId).orElseThrow(() -> new Error());
   return groupListResponseMapper.convertToDto(groupList);

    //TODO зробити помилку
  }


  @Override
  public GroupListResponse create(GroupListResponse obj) {
 return null;
  }

  public GroupListResponse create(GroupListRequest obj) {
    GroupList groupList = groupListRequestMapper.convertToEntity(obj);
    GroupList save = groupListRepository.save(groupList);
    return groupListResponseMapper.convertToDto(save);

  }

  @Override
  public void update(GroupListResponse obj) {
  }
  public void update(GroupListRequest obj) {
    GroupList concertGroupList = groupListRequestMapper.convertToEntity(obj);
    GroupList findGroupList =
      groupListRepository.findByGroup(concertGroupList.getGroup()).orElseThrow(() -> new Error());

    GroupList groupStatus =
      GroupList.builder().id(findGroupList.getId()).group(concertGroupList.getGroup())
        .groupGraduation(concertGroupList.getGroupGraduation()).mentor(concertGroupList.getMentor())
        .program(concertGroupList.getProgram()).coordinator(concertGroupList.getCoordinator())
        .trainer(concertGroupList.getTrainer()).build();
    groupListRepository.save(groupStatus);
  }

  @Override
  public void delete(Long userId) {
    GroupList findGroupStatus = groupListRepository.findById(userId).orElseThrow(() -> new Error());

    //TODO зробити помилку
    groupListRepository.save(findGroupStatus);

  }
}
