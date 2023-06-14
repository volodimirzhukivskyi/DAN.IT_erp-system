package com.danit.erp.facade.card.invoice;

import com.danit.erp.domain.card.invoice.InvoiceCard;
import com.danit.erp.dto.card.invoice.InvoiceCardResponse;
import com.danit.erp.dto.card.invoice.PageInvoiceCardResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PageInvoiceCardResponseMapper {
  private final InvoiceCardResponseMapper invoiceCardResponseMapper;

  public PageInvoiceCardResponseMapper(InvoiceCardResponseMapper invoiceCardResponseMapper) {

    this.invoiceCardResponseMapper = invoiceCardResponseMapper;
  }

  public PageInvoiceCardResponse convertToDto(Page<InvoiceCard> entity) {
    PageInvoiceCardResponse dto = new PageInvoiceCardResponse();

    dto.setTotalPages(entity.getTotalPages());
    dto.setTotalElements(entity.getTotalElements());
    List<InvoiceCard> invoiceCards = entity.getContent();
    if (invoiceCards.size() > 0) {
      List<InvoiceCardResponse> invoiceCardResponses =
        invoiceCards.stream().map(invoiceCardResponseMapper::convertToDto).toList();
      dto.setContent(invoiceCardResponses);
    }
    return dto;
  }


}