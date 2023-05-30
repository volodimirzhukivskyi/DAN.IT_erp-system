package com.danit.erp.controller;

import com.danit.erp.domain.personalcard.PersonalCard;
import com.danit.erp.service.PersonalCardService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("${api.version}/personal_cards")
public class PersonalCardController {
  private final PersonalCardService personalCardService;

  @GetMapping("/")
  public List<PersonalCard> getAllTweets() {
    return personalCardService.findAll();
  }

}
