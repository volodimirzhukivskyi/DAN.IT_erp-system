package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.PaymentMethod;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.PaymentMethodRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentMethodService implements BaseService<PaymentMethod> {
  private final PaymentMethodRepository paymentMethodRepository;

  @Override
  public List<PaymentMethod> findAll() {
    return paymentMethodRepository.findByDeletedFalse();
  }

  @Override
  public List<PaymentMethod> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public PaymentMethod findById(Long userId) {
    return paymentMethodRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new CouldNotFindException("Метода оплати"));
  }


  @Override
  public PaymentMethod create(PaymentMethod obj) {
    PaymentMethod paymentMethod = PaymentMethod.builder().method(obj.getMethod()).build();
    return paymentMethodRepository.save(paymentMethod);
  }

  @Override
  public void update(PaymentMethod obj) {
    PaymentMethod findPaymentMethod =
      paymentMethodRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new CouldNotFindException("Метода оплати"));

    PaymentMethod paymentMethod =
      PaymentMethod.builder().id(findPaymentMethod.getId()).method(obj.getMethod()).build();
    paymentMethodRepository.save(paymentMethod);
  }

  @Override
  public void delete(Long userId) {
    PaymentMethod findPaymentMethod =
      paymentMethodRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Метода оплати"));

    findPaymentMethod.setDeleted(true);
    paymentMethodRepository.save(findPaymentMethod);

  }
}
