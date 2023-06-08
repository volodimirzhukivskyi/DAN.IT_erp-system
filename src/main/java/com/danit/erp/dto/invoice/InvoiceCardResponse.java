package com.danit.erp.dto.invoice;

import com.danit.erp.domain.contract.Contract;
import com.danit.erp.domain.dictionary.PaymentMethod;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class InvoiceCardResponse {
  private Long id;
  private Long invoiceNo;
  private Long contractNo;
  private String responsibleManager;
  private String dueDate;
  private Double amountDue;
  private String paymentDate;
  private Double amountPaid;
  private   String paymentMethod;
}
