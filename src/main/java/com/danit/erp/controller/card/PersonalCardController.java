package com.danit.erp.controller.card;

import com.danit.erp.dto.card.personal_card.PagePersonalCardResponse;
import com.danit.erp.dto.card.personal_card.PersonalCardRequest;
import com.danit.erp.dto.card.personal_card.PersonalCardResponse;
import com.danit.erp.service.card.PersonalCardService;
import lombok.AllArgsConstructor;
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
@RequestMapping("${api.version}/personal_card")
public class PersonalCardController {
  private final PersonalCardService personalCardService;

  @GetMapping("/")
  public PagePersonalCardResponse getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return personalCardService.getAllPage(pageSize, pageNumber);
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
