package com.danit.erp.controller;

import com.danit.erp.domain.personalcard.Contract;
import com.danit.erp.service.ContractService;
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
@RequestMapping("${api.version}/contract")
public class ContractController {
  private final ContractService contractService;

  @GetMapping("/")
  public List<Contract> getAllTwees() {
    return contractService.findAll();
  }

  @GetMapping("/{id}")
  public Contract getById(@PathVariable("id") String userId) throws Exception {
    return contractService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    contractService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody Contract personalCard) {
     contractService.update(personalCard);
    }



  @PostMapping("/create")
  public Contract create(@RequestBody Contract personalCard) {
    return contractService.create(personalCard);
  }
}
