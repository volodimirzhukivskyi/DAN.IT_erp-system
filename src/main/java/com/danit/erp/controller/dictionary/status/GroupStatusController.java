package com.danit.erp.controller.dictionary.status;

import com.danit.erp.domain.dictionary.status.GroupStatus;
import com.danit.erp.service.dictionary.status.GroupStatusService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("${api.version}/group_status")
public class GroupStatusController {
  private final GroupStatusService groupStatusService;

  @GetMapping("/")
  public List<GroupStatus> getAll() {
    return groupStatusService.findAll();
  }

  @GetMapping("/{id}")
  public GroupStatus getById(@PathVariable("id") String userId) throws Exception {
    return groupStatusService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    groupStatusService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody GroupStatus personalCard) {
    groupStatusService.update(personalCard);
  }



  @PostMapping("/create")
  public GroupStatus create(@RequestBody GroupStatus personalCard) {
    return groupStatusService.create(personalCard);
  }
}
