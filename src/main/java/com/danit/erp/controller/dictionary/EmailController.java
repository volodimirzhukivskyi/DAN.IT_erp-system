package com.danit.erp.controller.dictionary;

import com.danit.erp.domain.dictionary.Email;
import com.danit.erp.service.dictionary.EmailListService;
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
@RequestMapping("${api.version}/email_list")
public class EmailController {
  private final EmailListService emailListService;

  @GetMapping("/")
  public List<Email> getAll() {
    return emailListService.findAll();
  }

  @GetMapping("/{id}")
  public Email getById(@PathVariable("id") String userId) throws Exception {
    return emailListService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    emailListService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Email personalCard) {
    emailListService.update(personalCard);
  }



  @PostMapping("/create")
  public Email create(@RequestBody Email personalCard) {
    return emailListService.create(personalCard);
  }
}
