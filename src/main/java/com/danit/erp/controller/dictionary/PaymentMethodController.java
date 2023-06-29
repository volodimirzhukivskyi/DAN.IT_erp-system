package com.danit.erp.controller.dictionary;

import com.danit.erp.domain.dictionary.PaymentMethod;
import com.danit.erp.service.dictionary.PaymentMethodService;
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
@RequestMapping("${api.version}/payment_method")
public class PaymentMethodController {
  private final PaymentMethodService paymentMethodService;

  @GetMapping("/")
  public Page<PaymentMethod> getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return paymentMethodService.getAllPageable(pageSize, pageNumber);
  }

  @GetMapping("/{id}")
  public PaymentMethod getById(@PathVariable("id") String userId) throws Exception {
    return paymentMethodService.findById(Byte.parseByte(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    paymentMethodService.delete(Byte.parseByte(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody PaymentMethod personalCard) {
    paymentMethodService.update(personalCard);
  }


  @PostMapping("/create")
  public PaymentMethod create(@RequestBody PaymentMethod personalCard) {
    return paymentMethodService.create(personalCard);
  }
}
