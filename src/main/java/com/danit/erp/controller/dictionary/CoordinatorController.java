package com.danit.erp.controller.dictionary;

import com.danit.erp.domain.dictionary.Coordinator;
import com.danit.erp.service.dictionary.CoordinatorService;
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
@RequestMapping("${api.version}/coordinator")
public class CoordinatorController {
  private final CoordinatorService coordinatorService;

  @GetMapping("/")
  public List<Coordinator> getAll() {
    return coordinatorService.findAll();
  }

  @GetMapping("/{id}")
  public Coordinator getById(@PathVariable("id") String userId) throws Exception {
    return coordinatorService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    coordinatorService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Coordinator personalCard) {
    coordinatorService.update(personalCard);
  }



  @PostMapping("/create")
  public Coordinator create(@RequestBody Coordinator personalCard) {
    return coordinatorService.create(personalCard);
  }
}
