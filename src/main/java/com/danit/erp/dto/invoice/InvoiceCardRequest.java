package com.danit.erp.dto.invoice;


import com.danit.erp.domain.contract.Contract;
import com.danit.erp.domain.dictionary.PaymentMethod;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class InvoiceCardRequest {
  private Long invoiceNo;
  private Long contractNo;
  private LocalDateTime dueDate;
  private Double amountDue;
  private LocalDateTime paymentDate;
  private Double amountPaid;
  private String paymentMethod;


}
