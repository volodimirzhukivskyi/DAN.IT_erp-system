package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.ContractStatus;
import com.danit.erp.repository.dictionary.ContractStatusRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractStatusService implements BaseService<ContractStatus> {
  private final ContractStatusRepository contractStatusRepository;

  @Override
  public List<ContractStatus> findAll() {
    return contractStatusRepository.findByDeletedFalse();
  }

  @Override
  public List<ContractStatus> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public ContractStatus findById(Long userId) {
    return contractStatusRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку
  }


  @Override
  public ContractStatus create(ContractStatus obj) {
    ContractStatus contractStatus = ContractStatus.builder().status(obj.getStatus()).build();
    return contractStatusRepository.save(contractStatus);
  }

  @Override
  public void update(ContractStatus obj) {
    ContractStatus findContractStatus =
      contractStatusRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new Error());

    ContractStatus contractStatus =
      ContractStatus.builder().id(findContractStatus.getId()).status(obj.getStatus()).build();
    contractStatusRepository.save(contractStatus);
  }

  @Override
  public void delete(Long userId) {
    ContractStatus role =
      contractStatusRepository.findById(userId).orElseThrow(() -> new Error());

    //TODO зробити помилку
    role.setDeleted(true);
    contractStatusRepository.save(role);

  }
}
