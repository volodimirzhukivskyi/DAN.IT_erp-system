package com.danit.erp.service.card;

import com.danit.erp.domain.card.invoice.InvoiceCard;
import com.danit.erp.dto.card.invoice.InvoiceCardRequest;
import com.danit.erp.dto.card.invoice.InvoiceCardResponse;
import com.danit.erp.facade.card.invoice.InvoiceCardRequestMapper;
import com.danit.erp.facade.card.invoice.InvoiceCardResponseMapper;
import com.danit.erp.repository.card.InvoiceCardRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceCardService implements BaseService<InvoiceCardResponse> {
  private final InvoiceCardRepository invoiceCardRepository;

  private final InvoiceCardRequestMapper invoiceCardRequestMapper;
  private final InvoiceCardResponseMapper invoiceCardResponseMapper;

  @Override
  public List<InvoiceCardResponse> findAll() {

    List<InvoiceCard> invoiceList = invoiceCardRepository.findAll();
    return invoiceList.stream().map(invoiceCardResponseMapper::convertToDto)
      .collect(Collectors.toList());
  }

  @Override
  public List<InvoiceCardResponse> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public InvoiceCardResponse findById(Long userId) {
    InvoiceCard invoiceCard = invoiceCardRepository.findById(userId).orElseThrow(() -> new Error());
    return invoiceCardResponseMapper.convertToDto(invoiceCard);

    //TODO зробити помилку або глянути чи вона взагалі потрібна
  }

  @Override
  public void update(InvoiceCardResponse obj) {

  }

  @Override
  public InvoiceCardResponse create(InvoiceCardResponse obj) {
    return null;
  }

  public InvoiceCardResponse create(InvoiceCardRequest invoiceCardRequest) {
    //TODO  - логіку перенести в dtoMapper
    InvoiceCard obj = invoiceCardRequestMapper.convertToEntity(invoiceCardRequest);

    InvoiceCard invoiceCard =
      InvoiceCard.builder().contract(obj.getContract()).invoiceNo(obj.getInvoiceNo())
        .dueDate(obj.getDueDate()).amountDue(obj.getAmountDue()).amountPaid(obj.getAmountPaid())
        .paymentDate(obj.getPaymentDate()).paymentMethod(obj.getPaymentMethod()).build();
    InvoiceCard saveInvoiceCard = invoiceCardRepository.save(invoiceCard);

    return invoiceCardResponseMapper.convertToDto(saveInvoiceCard);
  }

  public void update(InvoiceCardRequest invoiceCardRequest) {

    InvoiceCard obj = invoiceCardRequestMapper.convertToEntity(invoiceCardRequest);
    InvoiceCard findInvoiceCard =
      invoiceCardRepository.findById(obj.getId()).orElseThrow(() -> new Error());

    InvoiceCard invoiceCard =
      InvoiceCard.builder().id(findInvoiceCard.getId()).contract(obj.getContract()).invoiceNo(obj.getInvoiceNo())
        .dueDate(obj.getDueDate()).amountDue(obj.getAmountDue()).amountPaid(obj.getAmountPaid())
        .paymentDate(obj.getPaymentDate()).paymentMethod(obj.getPaymentMethod()).build();
     invoiceCardRepository.save(invoiceCard);
  }

  @Override
  public void delete(Long userId) {
    InvoiceCard invoiceCard = invoiceCardRepository.findById(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку

    invoiceCardRepository.delete(invoiceCard);
  }
}
