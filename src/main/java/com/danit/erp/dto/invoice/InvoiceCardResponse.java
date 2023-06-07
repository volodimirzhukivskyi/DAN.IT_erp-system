package com.danit.erp.dto.invoice;

import com.danit.erp.domain.contract.Contract;
import com.danit.erp.domain.dictionary.PaymentMethod;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class InvoiceCardResponse {
  private Long invoiceNo;
  private Contract contract;
  private LocalDateTime dueDate;
  private Double amountDue;
  private LocalDateTime paymentDate;
  private Double amountPaid;
  private PaymentMethod paymentMethod;
}
