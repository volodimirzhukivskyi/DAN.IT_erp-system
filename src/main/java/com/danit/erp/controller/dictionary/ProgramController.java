package com.danit.erp.controller.dictionary;

import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.service.dictionary.ProgramService;
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
@RequestMapping("${api.version}/program")
public class ProgramController {
  private final ProgramService programService;

  @GetMapping("/")
  public List<Program> getAll() {
    return programService.findAll();
  }

  @GetMapping("/{id}")
  public Program getById(@PathVariable("id") String userId) throws Exception {
    return programService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    programService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Program personalCard) {
    programService.update(personalCard);
  }



  @PostMapping("/create")
  public Program create(@RequestBody Program personalCard) {
    return programService.create(personalCard);
  }
}
