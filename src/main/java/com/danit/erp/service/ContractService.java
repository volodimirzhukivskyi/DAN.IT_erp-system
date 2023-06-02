package com.danit.erp.service;

import com.danit.erp.domain.contract.Contract;
import com.danit.erp.domain.dictionary.LegalEntity;
import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.domain.personalcard.PersonalCard;
import com.danit.erp.repository.ContractRepository;
import com.danit.erp.repository.PersonalCardRepository;
import com.danit.erp.repository.dictionary.LegalEntityRepository;
import com.danit.erp.repository.dictionary.ProgramRepository;
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
  private final ProgramRepository programRepository;
  private final LegalEntityRepository legalEntityRepository;

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
    Optional<Program> program =
      programRepository.findByProgram(obj.getProgram().getProgram());

    Optional<LegalEntity> legalEntity =
      legalEntityRepository.findByIdCode(obj.getLegalEntity().getIdCode());
//! Варіант 1
//    PersonalCard saveInitialCard = personalCard.orElse(null);
//    String saveName = saveInitialCard == null ? obj.getClientName() :
//      String.format("%s " + "%s", personalCard.get().getSurname(), personalCard.get().getName());
//! Варіант 2
    //TODO   в дто значення змінити
    PersonalCard saveInitialCard = personalCard.orElseThrow(() -> new Error());
    Program saveInitialProgram = program.orElseThrow(() -> new Error());
    LegalEntity saveLegalEntity  =legalEntity.orElseThrow(()->new Error());
    Contract contract =
      Contract.builder().contractNo(obj.getContractNo()).contractDate(obj.getContractDate())
        .coordinator(obj.getCoordinator()).personalCard(saveInitialCard)
        .contractStatus(obj.getContractStatus()).legalEntity(saveLegalEntity).program(saveInitialProgram).manager(obj.getManager())
        .contractValue(obj.getContractValue()).docLink(obj.getDocLink()).build();
    return contractRepository.save(contract);
  }

  @Override
  public void update(Contract obj) {
    Contract findContract = contractRepository.findById(obj.getId()).orElseThrow(() -> new Error());

    Contract contract = Contract.builder().id(findContract.getId()).contractNo(obj.getContractNo())
      .contractDate(obj.getContractDate()).personalCard(obj.getPersonalCard())
      .manager(obj.getManager()).legalEntity(obj.getLegalEntity()).coordinator(obj.getCoordinator()).program(obj.getProgram())
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
