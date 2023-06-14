package com.danit.erp.controller.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Role;
import com.danit.erp.service.dictionary.roles.RoleService;
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
@RequestMapping("${api.version}/role")
public class RoleController {
  private final RoleService roleService;

  @GetMapping("/")
  public Page<Role> getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return roleService.getAllPageable(pageSize, pageNumber);
  }

  @GetMapping("/{id}")
  public Role getById(@PathVariable("id") String userId) throws Exception {
    return roleService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    roleService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Role role) {
    roleService.update(role);
  }


  @PostMapping("/create")
  public Role create(@RequestBody Role role) {
    return roleService.create(role);
  }
}
