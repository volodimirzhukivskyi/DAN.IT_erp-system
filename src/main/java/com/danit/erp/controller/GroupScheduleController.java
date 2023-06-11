package com.danit.erp.controller;


import com.danit.erp.dto.group_schedule.GroupScheduleDto;
import com.danit.erp.service.GroupScheduleService;
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
@RequestMapping("${api.version}/group_schedule")
public class GroupScheduleController {
  private final GroupScheduleService groupScheduleService;

  @GetMapping("/")
  public List<GroupScheduleDto> getAll() {
    return groupScheduleService.findAll();
  }

  @GetMapping("/{id}")
  public GroupScheduleDto getById(@PathVariable("id") String userId) throws Exception {
    return groupScheduleService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    groupScheduleService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody GroupScheduleDto groupScheduleDto) {
     groupScheduleService.update(groupScheduleDto);
    }



  @PostMapping("/create")
  public GroupScheduleDto create(@RequestBody GroupScheduleDto groupScheduleDto) {
    return groupScheduleService.create(groupScheduleDto);
  }
}
