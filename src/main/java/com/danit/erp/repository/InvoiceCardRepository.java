package com.danit.erp.repository;

import com.danit.erp.domain.invoice.InvoiceCard;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceCardRepository extends JpaRepository<InvoiceCard,Long> {
  Optional<InvoiceCard> findByInvoiceNo(Long Invoice);
}
