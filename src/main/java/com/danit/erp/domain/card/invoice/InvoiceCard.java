package com.danit.erp.domain.card.invoice;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.card.contract.Contract;
import com.danit.erp.domain.dictionary.PaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "invoice_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class InvoiceCard extends BaseEntity<Long> {
  @Column(name = "invoice_no")
  private Long invoiceNo;
  @OneToOne(targetEntity = Contract.class)
  @JoinColumn(name = "contract", referencedColumnName = "contract_no")
  private Contract contract;
  private LocalDateTime dueDate;
  private Double amountDue;
  private LocalDateTime paymentDate;
  private Double amountPaid;
  @ManyToOne
  private PaymentMethod paymentMethod;

}
