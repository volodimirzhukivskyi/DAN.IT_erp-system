package com.danit.erp.controller.dictionary;

import com.danit.erp.domain.dictionary.LegalEntity;
import com.danit.erp.service.dictionary.LegalEntityService;
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
@RequestMapping("${api.version}/legal_entity")
public class LegalEntityController {
  private final LegalEntityService legalEntityService;

  @GetMapping("/")
  public Page<LegalEntity> getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return legalEntityService.getAllPageable(pageSize, pageNumber);
  }

  @GetMapping("/{id}")
  public LegalEntity getById(@PathVariable("id") String userId) throws Exception {
    return legalEntityService.findById(Integer.parseInt(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    legalEntityService.delete(Integer.parseInt(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody LegalEntity personalCard) {
    legalEntityService.update(personalCard);
  }


  @PostMapping("/create")
  public LegalEntity create(@RequestBody LegalEntity personalCard) {
    return legalEntityService.create(personalCard);
  }
}
