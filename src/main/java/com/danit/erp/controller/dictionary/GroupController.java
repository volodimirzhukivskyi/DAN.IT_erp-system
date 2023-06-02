package com.danit.erp.controller.dictionary;

import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.service.dictionary.GroupService;
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
@RequestMapping("${api.version}/group")
public class GroupController {
  private final GroupService groupService;

  @GetMapping("/")
  public List<Group> getAll() {
    return groupService.findAll();
  }

  @GetMapping("/{id}")
  public Group getById(@PathVariable("id") String userId) throws Exception {
    return groupService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    groupService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Group personalCard) {
    groupService.update(personalCard);
  }



  @PostMapping("/create")
  public Group create(@RequestBody Group personalCard) {
    return groupService.create(personalCard);
  }
}
