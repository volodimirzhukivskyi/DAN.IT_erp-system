package com.danit.erp.repository.card;

import com.danit.erp.domain.card.contract.Contract;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {
  Optional<Contract> findByContractNo(Long contractNo);
}
