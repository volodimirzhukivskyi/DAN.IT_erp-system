package com.danit.erp.controller.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Mentor;
import com.danit.erp.service.dictionary.roles.MentorService;
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
@RequestMapping("${api.version}/mentor")
public class MentorController {
  private final MentorService mentorService;

  @GetMapping("/")
  public Page<Mentor> getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return mentorService.getAllPageable(pageSize, pageNumber);
  }

  @GetMapping("/{id}")
  public Mentor getById(@PathVariable("id") String userId) throws Exception {
    return mentorService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    mentorService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Mentor mentor) {
    mentorService.update(mentor);
  }


  @PostMapping("/create")
  public Mentor create(@RequestBody Mentor mentor) {
    return mentorService.create(mentor);
  }
}
