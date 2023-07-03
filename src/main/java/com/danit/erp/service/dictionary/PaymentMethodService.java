package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.PaymentMethod;
import com.danit.erp.exception.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.PaymentMethodRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentMethodService implements BaseService<PaymentMethod,Byte> {
  private final PaymentMethodRepository paymentMethodRepository;

  @Override
  public List<PaymentMethod> findAll() {
    return paymentMethodRepository.findByDeletedFalse();
  }

  @Override
  public Page<PaymentMethod> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return paymentMethodRepository.findByDeletedFalse(pageable);
  }

  @Override
  public PaymentMethod findById(Byte userId) {
    return paymentMethodRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("The payment method"));
  }


  @Override
  public PaymentMethod create(PaymentMethod obj) {
    PaymentMethod paymentMethod = PaymentMethod.builder().method(obj.getMethod()).build();
    return paymentMethodRepository.save(paymentMethod);
  }

  @Override
  public void update(PaymentMethod obj) {
    PaymentMethod findPaymentMethod = paymentMethodRepository.findByIdAndDeletedFalse(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("The payment method"));

    PaymentMethod paymentMethod =
      PaymentMethod.builder().id(findPaymentMethod.getId()).method(obj.getMethod()).build();
    paymentMethodRepository.save(paymentMethod);
  }

  @Override
  public void delete(Byte userId) {
    PaymentMethod findPaymentMethod = paymentMethodRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("The payment method"));

    findPaymentMethod.setDeleted(true);
    paymentMethodRepository.save(findPaymentMethod);

  }
}
