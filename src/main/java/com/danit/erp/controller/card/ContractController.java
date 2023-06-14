package com.danit.erp.controller.card;


import com.danit.erp.dto.card.contract.ContractRequest;
import com.danit.erp.dto.card.contract.ContractResponse;
import com.danit.erp.dto.card.contract.PageContractResponse;
import com.danit.erp.service.card.ContractService;
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
@RequestMapping("${api.version}/contract")
public class ContractController {
  private final ContractService contractService;

  @GetMapping("/")
  public PageContractResponse getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return contractService.getAllPage(pageSize, pageNumber);
  }

  @GetMapping("/{id}")
  public ContractResponse getById(@PathVariable("id") String userId) throws Exception {
    return contractService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    contractService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody ContractRequest contractRequest) {
    contractService.update(contractRequest);
  }


  @PostMapping("/create")
  public ContractResponse create(@RequestBody ContractRequest contractRequest) {
    return contractService.create(contractRequest);
  }
}
