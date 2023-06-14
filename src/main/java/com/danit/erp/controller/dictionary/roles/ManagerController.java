package com.danit.erp.controller.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Manager;
import com.danit.erp.service.dictionary.roles.ManagerService;
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
@RequestMapping("${api.version}/manager")
public class ManagerController {
  private final ManagerService managerService;

  @GetMapping("/")
  public Page<Manager> getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return managerService.getAllPageable(pageSize, pageNumber);
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
  public void update(@RequestBody Manager manager) {
    managerService.update(manager);
  }


  @PostMapping("/create")
  public Manager create(@RequestBody Manager manager) {
    return managerService.create(manager);
  }
}
