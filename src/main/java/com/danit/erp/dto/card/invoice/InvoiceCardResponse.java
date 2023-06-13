package com.danit.erp.dto.card.invoice;

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
