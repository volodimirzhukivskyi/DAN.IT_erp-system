package com.danit.erp.controller;

import com.danit.erp.domain.personal_card.PersonalCard;
import com.danit.erp.dto.personal_card.PersonalCardRequest;
import com.danit.erp.dto.personal_card.PersonalCardResponse;
import com.danit.erp.service.PersonalCardService;
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
@RequestMapping("${api.version}/personal_card")
public class PersonalCardController {
  private final PersonalCardService personalCardService;

  @GetMapping("/")
  public List<PersonalCardResponse> getAll() {
    return personalCardService.findAll();
  }

  @GetMapping("/{id}")
  public PersonalCardResponse getById(@PathVariable("id") String userId) throws Exception {
    return personalCardService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    personalCardService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody PersonalCardRequest personalCard) {
     personalCardService.update(personalCard);
    }



  @PostMapping("/create")
  public PersonalCardResponse create(@RequestBody PersonalCardRequest personalCard) {
    return personalCardService.create(personalCard);
  }
}
