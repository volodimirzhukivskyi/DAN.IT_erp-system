package com.danit.erp.controller;

import com.danit.erp.domain.dictionary.University;
import com.danit.erp.service.dictionary.UniversityService;
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
@RequestMapping("${api.version}/university")
public class UniversityController {
  private final UniversityService universityService;

  @GetMapping("/")
  public List<University> getAll() {
    return universityService.findAll();
  }

  @GetMapping("/{id}")
  public University getById(@PathVariable("id") String userId) throws Exception {
    return universityService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    universityService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody University personalCard) {
    universityService.update(personalCard);
  }



  @PostMapping("/create")
  public University create(@RequestBody University personalCard) {
    return universityService.create(personalCard);
  }
}
