package com.danit.erp.controller;


import com.danit.erp.dto.invoice.InvoiceCardRequest;
import com.danit.erp.dto.invoice.InvoiceCardResponse;
import com.danit.erp.service.InvoiceCardService;
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
@RequestMapping("${api.version}/invoice_card")
public class InvoiceCardController {
  private final InvoiceCardService invoiceCardService;

  @GetMapping("/")
  public List<InvoiceCardResponse> getAll() {
    return invoiceCardService.findAll();
  }

  @GetMapping("/{id}")
  public InvoiceCardResponse getById(@PathVariable("id") String userId) throws Exception {
    return invoiceCardService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    invoiceCardService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody InvoiceCardRequest invoiceCardRequest) {
     invoiceCardService.update(invoiceCardRequest);
    }



  @PostMapping("/create")
  public InvoiceCardResponse create(@RequestBody InvoiceCardRequest invoiceCardRequest) {
    return invoiceCardService.create(invoiceCardRequest);
  }
}
