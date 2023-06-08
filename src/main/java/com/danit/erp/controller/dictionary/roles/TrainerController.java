package com.danit.erp.controller.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Trainer;
import com.danit.erp.service.dictionary.roles.TrainerService;
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
@RequestMapping("${api.version}/trainer")
public class TrainerController {
  private final TrainerService trainerService;

  @GetMapping("/")
  public List<Trainer> getAll() {
    return trainerService.findAll();
  }

  @GetMapping("/{id}")
  public Trainer getById(@PathVariable("id") String userId) throws Exception {
    return trainerService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    trainerService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Trainer trainer) {
    trainerService.update(trainer);
  }



  @PostMapping("/create")
  public Trainer create(@RequestBody Trainer trainer) {
    return trainerService.create(trainer);
  }
}
