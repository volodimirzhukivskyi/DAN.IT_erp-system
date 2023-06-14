package com.danit.erp.controller.dictionary.status;

import com.danit.erp.domain.dictionary.status.ContractStatus;
import com.danit.erp.service.dictionary.status.ContractStatusService;
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
@RequestMapping("${api.version}/contract_status")
public class ContractStatusController {
  private final ContractStatusService contractStatusService;

  @GetMapping("/")
  public Page<ContractStatus> getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return contractStatusService.getAllPageable(pageSize, pageNumber);
  }

  @GetMapping("/{id}")
  public ContractStatus getById(@PathVariable("id") String userId) throws Exception {
    return contractStatusService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    contractStatusService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody ContractStatus personalCard) {
    contractStatusService.update(personalCard);
  }


  @PostMapping("/create")
  public ContractStatus create(@RequestBody ContractStatus personalCard) {
    return contractStatusService.create(personalCard);
  }
}
