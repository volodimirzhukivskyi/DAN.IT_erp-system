package com.danit.erp.repository.card;

import com.danit.erp.domain.card.invoice.InvoiceCard;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceCardRepository extends JpaRepository<InvoiceCard,Long> {
  Optional<InvoiceCard> findByInvoiceNo(Long Invoice);
}
