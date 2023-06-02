package com.danit.erp.service;

import com.danit.erp.domain.contract.Contract;
import com.danit.erp.domain.personalcard.PersonalCard;
import com.danit.erp.repository.ContractRepository;
import com.danit.erp.repository.PersonalCardRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractService implements BaseService<Contract> {
  private final ContractRepository contractRepository;
  private final PersonalCardRepository personalCardRepository;

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
    Optional<PersonalCard> personalCard =
      personalCardRepository.findByIdCode(obj.getPersonalCard().getIdCode());
//! Варіант 1
//    PersonalCard saveInitialCard = personalCard.orElse(null);
//    String saveName = saveInitialCard == null ? obj.getClientName() :
//      String.format("%s " + "%s", personalCard.get().getSurname(), personalCard.get().getName());
//! Варіант 2
    PersonalCard saveInitialCard = personalCard.orElseThrow(() -> new Error());
    Contract contract =
      Contract.builder().contractNo(obj.getContractNo()).contractDate(obj.getContractDate())
        .coordinator(obj.getCoordinator()).personalCard(saveInitialCard)
        .contractStatus(obj.getContractStatus()).manager(obj.getManager())
        .contractValue(obj.getContractValue()).docLink(obj.getDocLink()).build();
    return contractRepository.save(contract);
  }

  @Override
  public void update(Contract obj) {
    Contract findContract = contractRepository.findById(obj.getId()).orElseThrow(() -> new Error());

    Contract contract = Contract.builder().id(findContract.getId()).contractNo(obj.getContractNo())
      .contractDate(obj.getContractDate()).personalCard(obj.getPersonalCard())
      .manager(obj.getManager()).coordinator(obj.getCoordinator())
      .contractStatus(obj.getContractStatus()).contractValue(obj.getContractValue())
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
