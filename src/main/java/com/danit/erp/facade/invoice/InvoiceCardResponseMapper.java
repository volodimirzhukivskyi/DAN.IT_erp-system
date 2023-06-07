package com.danit.erp.facade.invoice;

import com.danit.erp.domain.invoice.InvoiceCard;
import com.danit.erp.dto.invoice.InvoiceCardResponse;
import com.danit.erp.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class InvoiceCardResponseMapper extends GeneralFacade<InvoiceCard, InvoiceCardResponse> {


  public InvoiceCardResponseMapper() {
    super(InvoiceCard.class, InvoiceCardResponse.class);

  }

  @Override
  protected void decorateDto(InvoiceCardResponse dto, InvoiceCard entity) {
    //TODO code here

    super.decorateDto(dto, entity);
  }

  @Override
  public void decorateEntity(InvoiceCard entity, InvoiceCardResponse dto) {


  }
}