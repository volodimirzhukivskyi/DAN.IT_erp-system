package com.danit.erp.controller.dictionary;

import com.danit.erp.domain.dictionary.Manager;
import com.danit.erp.service.dictionary.ManagerService;
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
@RequestMapping("${api.version}/manager")
public class ManagerController {
  private final ManagerService managerService;

  @GetMapping("/")
  public List<Manager> getAll() {
    return managerService.findAll();
  }

  @GetMapping("/{id}")
  public Manager getById(@PathVariable("id") String userId) throws Exception {
    return managerService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    managerService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Manager personalCard) {
    managerService.update(personalCard);
  }



  @PostMapping("/create")
  public Manager create(@RequestBody Manager personalCard) {
    return managerService.create(personalCard);
  }
}
