package com.danit.erp.facade.list.group_list;

import com.danit.erp.domain.list.group_list.GroupList;
import com.danit.erp.dto.list.group_list.GroupListResponse;
import com.danit.erp.dto.list.group_list.PageGroupListResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PageGroupListResponseMapper {
  private final GroupListResponseMapper groupListResponseMapper;

  public PageGroupListResponseMapper(GroupListResponseMapper groupListResponseMapper) {
    this.groupListResponseMapper = groupListResponseMapper;
  }

  public PageGroupListResponse convertToDto(Page<GroupList> entity) {
    PageGroupListResponse dto = new PageGroupListResponse();

    dto.setTotalPages(entity.getTotalPages());
    dto.setTotalElements(entity.getTotalElements());
    List<GroupList> groupLists = entity.getContent();
    if (groupLists.size() > 0) {
      List<GroupListResponse> groupListResponses =
        groupLists.stream().map(groupListResponseMapper::convertToDto).toList();
      dto.setContent(groupListResponses);
    }
    return dto;
  }


}