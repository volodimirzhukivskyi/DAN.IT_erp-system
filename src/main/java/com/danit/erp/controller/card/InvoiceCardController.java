package com.danit.erp.controller.card;


import com.danit.erp.dto.card.invoice.InvoiceCardRequest;
import com.danit.erp.dto.card.invoice.InvoiceCardResponse;
import com.danit.erp.dto.card.invoice.PageInvoiceCardResponse;
import com.danit.erp.service.card.InvoiceCardService;
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
@RequestMapping("${api.version}/invoice_card")
public class InvoiceCardController {
  private final InvoiceCardService invoiceCardService;

  @GetMapping("/")
  public PageInvoiceCardResponse getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
    return invoiceCardService.getAllPage(pageSize, pageNumber);
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
