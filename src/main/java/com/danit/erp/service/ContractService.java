package com.danit.erp.service;

import com.danit.erp.domain.personalcard.Contract;
import com.danit.erp.repository.ContractRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractService implements BaseService<Contract> {
  private final ContractRepository contractRepository;


  @Override
  public List<Contract> findAll() {
    return contractRepository.findAll();
  }

  @Override
  public List<Contract> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public Contract findById(Long userId) {
    return contractRepository.findById(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку або глянути чи вона взагалі потрібна
  }

  @Override
  public Contract create(Contract obj) {
    //TODO  - логіку перенести в dtoMapper
//    Optional<University> university= universityRepository.findByName(obj.getUniversity().getName());
//     University saveUniversity = university.orElseGet(obj::getUniversity);
//    Optional<Profession> profession= professionRepository.findByName(obj.getInitialProfession().getName());
//    Profession saveInitialProfession = profession.orElseGet(obj::getInitialProfession);

    Contract contract =
      Contract.builder().contractNo(obj.getContractNo()).contractDate(obj.getContractDate())
        .clientName(obj.getClientName())
//        .contractStatus(obj.getContractStatus())
        .contractValue(obj.getContractValue()).docLink(obj.getDocLink()).build();
    return contractRepository.save(contract);
  }

  @Override
  public void update(Contract obj) {
    Contract findContract = contractRepository.findById(obj.getId()).orElseThrow(() -> new Error());

    Contract contract = Contract.builder().id(findContract.getId()).contractNo(obj.getContractNo())
      .contractDate(obj.getContractDate()).clientName(obj.getClientName())
//      .contractStatus(obj.getContractStatus())
      .contractValue(obj.getContractValue())
      .docLink(obj.getDocLink()).build();
    contractRepository.save(contract);
  }

  @Override
  public void delete(Long userId) {
    Contract contract = contractRepository.findById(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку

    contractRepository.delete(contract);
  }
}
