package com.danit.erp.controller.dictionary;

import com.danit.erp.domain.dictionary.Role;
import com.danit.erp.service.dictionary.RoleService;
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
@RequestMapping("${api.version}/role")
public class RoleController {
  private final RoleService roleService;

  @GetMapping("/")
  public List<Role> getAll() {
    return roleService.findAll();
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
  public void update(@RequestBody Role personalCard) {
    roleService.update(personalCard);
  }



  @PostMapping("/create")
  public Role create(@RequestBody Role personalCard) {
    return roleService.create(personalCard);
  }
}
