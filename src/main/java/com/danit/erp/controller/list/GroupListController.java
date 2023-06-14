package com.danit.erp.controller.list;


import com.danit.erp.dto.list.group_list.GroupListRequest;
import com.danit.erp.dto.list.group_list.GroupListResponse;
import com.danit.erp.dto.list.group_list.PageGroupListResponse;
import com.danit.erp.service.list.GroupListService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("${api.version}/group_list")
public class GroupListController {
  private final GroupListService groupListService;

  @GetMapping("/")
  public PageGroupListResponse getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return groupListService.getAllPage(pageSize, pageNumber);
  }

  @GetMapping("/{id}")
  public GroupListResponse getById(@PathVariable("id") String userId) throws Exception {
    return groupListService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    groupListService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody GroupListRequest groupListRequest) {
    groupListService.update(groupListRequest);
  }


  @PostMapping("/create")
  public GroupListResponse create(@RequestBody GroupListRequest groupListRequest) {
    return groupListService.create(groupListRequest);
  }
}
