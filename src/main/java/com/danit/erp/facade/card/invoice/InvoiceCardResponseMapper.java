package com.danit.erp.facade.card.invoice;

import com.danit.erp.domain.card.invoice.InvoiceCard;
import com.danit.erp.dto.card.invoice.InvoiceCardResponse;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.utils.Helper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class InvoiceCardResponseMapper extends GeneralFacade<InvoiceCard, InvoiceCardResponse> {


  public InvoiceCardResponseMapper() {
    super(InvoiceCard.class, InvoiceCardResponse.class);

  }

  @Override
  protected void decorateDto(InvoiceCardResponse dto, InvoiceCard entity) {
    LocalDateTime dueDate = entity.getDueDate();
    LocalDateTime paymentDate = entity.getPaymentDate();

    dto.setContractNo(entity.getContract().getContractNo());
    dto.setResponsibleManager(entity.getContract().getManager().getFullName());
    dto.setPaymentMethod(entity.getPaymentMethod().getMethod());
    dto.setDueDate(Helper.convertDate(dueDate,"dd.MM.yyyy"));
    dto.setPaymentDate(Helper.convertDate(paymentDate,"dd.MM.yyyy"));
    super.decorateDto(dto, entity);
  }

  @Override
  public void decorateEntity(InvoiceCard entity, InvoiceCardResponse dto) {


  }
}