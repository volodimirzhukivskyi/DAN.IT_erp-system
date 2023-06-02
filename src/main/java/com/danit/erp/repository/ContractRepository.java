package com.danit.erp.repository;

import com.danit.erp.domain.personalcard.Contract;
import com.danit.erp.domain.personalcard.PersonalCard;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract,Long> {
  Optional<Contract> findByContractNo(Long contractNo);
}
