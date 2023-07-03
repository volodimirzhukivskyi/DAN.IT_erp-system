package com.danit.erp.controller.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Coordinator;
import com.danit.erp.service.dictionary.roles.CoordinatorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequestMapping("${api.version}/coordinator")
public class CoordinatorController {
  private final CoordinatorService coordinatorService;

  @GetMapping("/")
  public Page<Coordinator> getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return coordinatorService.getAllPageable(pageSize, pageNumber);
  }

  @GetMapping("/{id}")
  public Coordinator getById(@PathVariable("id") String userId) throws Exception {
    return coordinatorService.findById(Integer.parseInt(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    coordinatorService.delete(Integer.parseInt(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Coordinator coordinator) {
    coordinatorService.update(coordinator);
  }


  @PostMapping("/create")
  public Coordinator create(@RequestBody Coordinator coordinator) {
    return coordinatorService.create(coordinator);
  }
}
