package com.danit.erp.service.list;

import com.danit.erp.domain.list.group_list.GroupList;
import com.danit.erp.dto.list.group_list.GroupListRequest;
import com.danit.erp.dto.list.group_list.GroupListResponse;
import com.danit.erp.dto.list.group_list.PageGroupListResponse;
import com.danit.erp.exception.id.CouldNotFindException;
import com.danit.erp.facade.list.group_list.GroupListRequestMapper;
import com.danit.erp.facade.list.group_list.GroupListResponseMapper;
import com.danit.erp.facade.list.group_list.PageGroupListResponseMapper;
import com.danit.erp.repository.list.GroupListRepository;
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
public class GroupListService implements BaseService<GroupListResponse,Integer> {
  private final GroupListRepository groupListRepository;
  private final GroupListRequestMapper groupListRequestMapper;
  private final GroupListResponseMapper groupListResponseMapper;
  private final PageGroupListResponseMapper pageGroupListResponseMapper;

  @Override
  public List<GroupListResponse> findAll() {
    List<GroupList> all = groupListRepository.findAll();
    return all.stream().map(groupListResponseMapper::convertToDto).toList();
  }

  public PageGroupListResponse getAllPage(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    Page<GroupList> all = groupListRepository.findAll(pageable);
    return pageGroupListResponseMapper.convertToDto(all);
  }

  @Override
  public GroupListResponse findById(Integer userId) {
    GroupList groupList = groupListRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("The list of groups"));
    return groupListResponseMapper.convertToDto(groupList);

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
    GroupList findGroupList = groupListRepository.findByGroup(concertGroupList.getGroup())
      .orElseThrow(() -> new CouldNotFindException("The list of groups"));

    GroupList groupStatus =
      GroupList.builder().id(findGroupList.getId()).group(concertGroupList.getGroup())
        .groupGraduation(concertGroupList.getGroupGraduation()).mentor(concertGroupList.getMentor())
        .program(concertGroupList.getProgram()).coordinator(concertGroupList.getCoordinator())
        .trainer(concertGroupList.getTrainer()).build();
    groupListRepository.save(groupStatus);
  }

  @Override
  public void delete(Integer userId) {
    GroupList findGroupStatus = groupListRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("The list of groups"));

    groupListRepository.save(findGroupStatus);

  }
}
