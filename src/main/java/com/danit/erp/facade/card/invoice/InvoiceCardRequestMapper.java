package com.danit.erp.facade.card.invoice;

import com.danit.erp.domain.card.contract.Contract;
import com.danit.erp.domain.dictionary.PaymentMethod;
import com.danit.erp.domain.card.invoice.InvoiceCard;
import com.danit.erp.dto.card.invoice.InvoiceCardRequest;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.repository.card.ContractRepository;
import com.danit.erp.repository.card.InvoiceCardRepository;
import com.danit.erp.repository.dictionary.PaymentMethodRepository;
import org.springframework.stereotype.Service;

@Service

public class InvoiceCardRequestMapper extends GeneralFacade<InvoiceCard, InvoiceCardRequest> {
private  final InvoiceCardRepository invoiceCardRepository;
private  final PaymentMethodRepository paymentMethodRepository;
private  final ContractRepository contractRepository;

  public InvoiceCardRequestMapper(InvoiceCardRepository invoiceCardRepository,
                                  PaymentMethodRepository paymentMethodRepository,
                                  ContractRepository contractRepository) {
    super(InvoiceCard.class, InvoiceCardRequest.class);


    this.invoiceCardRepository = invoiceCardRepository;
    this.paymentMethodRepository = paymentMethodRepository;
    this.contractRepository = contractRepository;
  }

  @Override
  protected void decorateDto(InvoiceCardRequest dto, InvoiceCard entity) {

    super.decorateDto(dto, entity);
  }

  @Override
  protected void decorateEntity(InvoiceCard entity, InvoiceCardRequest dto) {
    //TODO code here
    PaymentMethod findPaymentMethod =
      paymentMethodRepository.findByMethod(dto.getPaymentMethod()).orElseThrow(()->new Error());
    Contract findContract =
      contractRepository.findByContractNo(dto.getContractNo()).orElseThrow(()->new Error());
    entity.setPaymentMethod(findPaymentMethod);
    entity.setContract(findContract);
    super.decorateEntity(entity, dto);
  }
}