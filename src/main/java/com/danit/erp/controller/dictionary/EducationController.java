package com.danit.erp.controller.dictionary;

import com.danit.erp.domain.dictionary.Education;
import com.danit.erp.service.dictionary.EducationService;
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
@RequestMapping("${api.version}/education")
public class EducationController {
  private final EducationService educationService;

  @GetMapping("/")
  public Page<Education> getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return educationService.getAllPageable(pageSize, pageNumber);
  }

  @GetMapping("/{id}")
  public Education getById(@PathVariable("id") String userId) throws Exception {
    return educationService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    educationService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Education personalCard) {
    educationService.update(personalCard);
  }


  @PostMapping("/create")
  public Education create(@RequestBody Education personalCard) {
    return educationService.create(personalCard);
  }
}
