package com.danit.erp.service.card;

import com.danit.erp.domain.card.invoice.InvoiceCard;
import com.danit.erp.dto.card.invoice.InvoiceCardRequest;
import com.danit.erp.dto.card.invoice.InvoiceCardResponse;
import com.danit.erp.dto.card.invoice.PageInvoiceCardResponse;
import com.danit.erp.exception.find.id.CouldNotFindInvoiceException;
import com.danit.erp.facade.card.invoice.InvoiceCardRequestMapper;
import com.danit.erp.facade.card.invoice.InvoiceCardResponseMapper;
import com.danit.erp.facade.card.invoice.PageInvoiceCardResponseMapper;
import com.danit.erp.repository.card.InvoiceCardRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceCardService implements BaseService<InvoiceCardResponse> {
  private final InvoiceCardRepository invoiceCardRepository;

  private final InvoiceCardRequestMapper invoiceCardRequestMapper;
  private final InvoiceCardResponseMapper invoiceCardResponseMapper;
  private final PageInvoiceCardResponseMapper pageInvoiceCardResponseMapper;

  @Override
  public List<InvoiceCardResponse> findAll() {

    List<InvoiceCard> invoiceList = invoiceCardRepository.findAll();
    return invoiceList.stream().map(invoiceCardResponseMapper::convertToDto)
      .collect(Collectors.toList());
  }

  public PageInvoiceCardResponse getAllPage(int size, int pageNumber) {

    Pageable pageable = PageRequest.of(pageNumber, size);
    Page<InvoiceCard> all = invoiceCardRepository.findAll(pageable);
    return pageInvoiceCardResponseMapper.convertToDto(all);
  }

  @Override
  public InvoiceCardResponse findById(Long userId) {
    InvoiceCard invoiceCard =
      invoiceCardRepository.findById(userId).orElseThrow(CouldNotFindInvoiceException::new);
    return invoiceCardResponseMapper.convertToDto(invoiceCard);

  }

  @Override
  public void update(InvoiceCardResponse obj) {

  }

  @Override
  public InvoiceCardResponse create(InvoiceCardResponse obj) {
    return null;
  }

  public InvoiceCardResponse create(InvoiceCardRequest invoiceCardRequest) {
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
      invoiceCardRepository.findById(obj.getId()).orElseThrow(CouldNotFindInvoiceException::new);

    InvoiceCard invoiceCard =
      InvoiceCard.builder().id(findInvoiceCard.getId()).contract(obj.getContract())
        .invoiceNo(obj.getInvoiceNo()).dueDate(obj.getDueDate()).amountDue(obj.getAmountDue())
        .amountPaid(obj.getAmountPaid()).paymentDate(obj.getPaymentDate())
        .paymentMethod(obj.getPaymentMethod()).build();
    invoiceCardRepository.save(invoiceCard);
  }

  @Override
  public void delete(Long userId) {
    InvoiceCard invoiceCard =
      invoiceCardRepository.findById(userId).orElseThrow(CouldNotFindInvoiceException::new);

    invoiceCardRepository.delete(invoiceCard);
  }
}
