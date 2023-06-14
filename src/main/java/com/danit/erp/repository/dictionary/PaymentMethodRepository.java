package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.PaymentMethod;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Long> {
  Optional<PaymentMethod> findByMethod(String status);
  Page<PaymentMethod> findByDeletedFalse(Pageable pageable);
  List<PaymentMethod> findByDeletedFalse();
  Optional<PaymentMethod> findByIdAndDeletedFalse(Long userId);
}
