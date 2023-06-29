package com.danit.erp.service.dictionary.status;

import com.danit.erp.domain.dictionary.status.ContractStatus;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.status.ContractStatusRepository;
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
public class ContractStatusService implements BaseService<ContractStatus,Byte> {
  private final ContractStatusRepository contractStatusRepository;

  @Override
  public List<ContractStatus> findAll() {
    return contractStatusRepository.findByDeletedFalse();
  }

  @Override
  public Page<ContractStatus> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return contractStatusRepository.findByDeletedFalse(pageable);
  }

  @Override
  public ContractStatus findById(Byte userId) {
    return contractStatusRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("Контракт статус"));
  }


  @Override
  public ContractStatus create(ContractStatus obj) {
    ContractStatus contractStatus = ContractStatus.builder().status(obj.getStatus()).build();
    return contractStatusRepository.save(contractStatus);
  }

  @Override
  public void update(ContractStatus obj) {
    ContractStatus findContractStatus =
      contractStatusRepository.findByIdAndDeletedFalse(obj.getId())
        .orElseThrow(() -> new CouldNotFindException("Контракт статус"));

    ContractStatus contractStatus =
      ContractStatus.builder().id(findContractStatus.getId()).status(obj.getStatus()).build();
    contractStatusRepository.save(contractStatus);
  }

  @Override
  public void delete(Byte userId) {
    ContractStatus contractStatus = contractStatusRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("Контракт статус"));

    contractStatus.setDeleted(true);
    contractStatusRepository.save(contractStatus);

  }
}
