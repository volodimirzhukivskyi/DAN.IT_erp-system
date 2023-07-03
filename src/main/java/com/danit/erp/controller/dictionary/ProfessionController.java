package com.danit.erp.controller.dictionary;

import com.danit.erp.domain.dictionary.Profession;
import com.danit.erp.service.dictionary.ProfessionService;
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
@RequestMapping("${api.version}/profession")
public class ProfessionController {
  private final ProfessionService professionService;

  @GetMapping("/")
  public Page<Profession> getAllTweets(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return professionService.getAllPageable(pageSize, pageNumber);
  }

  @GetMapping("/{id}")
  public Profession getById(@PathVariable("id") String userId) throws Exception {
    return professionService.findById(Short.parseShort(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    professionService.delete(Short.parseShort(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Profession personalCard) {
    professionService.update(personalCard);
  }


  @PostMapping("/create")
  public Profession create(@RequestBody Profession personalCard) {
    return professionService.create(personalCard);
  }
}
