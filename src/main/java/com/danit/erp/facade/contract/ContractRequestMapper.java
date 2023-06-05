package com.danit.erp.facade.contract;

import com.danit.erp.domain.contract.Contract;
import com.danit.erp.domain.dictionary.ContractStatus;
import com.danit.erp.domain.dictionary.Coordinator;
import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.domain.dictionary.LegalEntity;
import com.danit.erp.domain.dictionary.Manager;
import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.domain.personal_card.PersonalCard;
import com.danit.erp.dto.contract.ContractRequest;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.repository.PersonalCardRepository;
import com.danit.erp.repository.dictionary.ContractStatusRepository;
import com.danit.erp.repository.dictionary.CoordinatorRepository;
import com.danit.erp.repository.dictionary.GroupRepository;
import com.danit.erp.repository.dictionary.LegalEntityRepository;
import com.danit.erp.repository.dictionary.ManagerRepository;
import com.danit.erp.repository.dictionary.ProgramRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service

public class ContractRequestMapper extends GeneralFacade<Contract, ContractRequest> {
  private final LegalEntityRepository legalEntityRepository;
  private final PersonalCardRepository personalCardRepository;
  private final ProgramRepository programRepository;
  private final ContractStatusRepository contractStatusRepository;
  private final ManagerRepository managerRepository;
  private final CoordinatorRepository coordinatorRepository;
  private final GroupRepository groupRepository;

  public ContractRequestMapper(
    LegalEntityRepository legalEntityRepository,
    PersonalCardRepository personalCardRepository, ProgramRepository programRepository,
    ContractStatusRepository contractStatusRepository, ManagerRepository managerRepository,
    CoordinatorRepository coordinatorRepository, GroupRepository groupRepository) {
    super(Contract.class, ContractRequest.class);
    this.legalEntityRepository = legalEntityRepository;
    this.personalCardRepository = personalCardRepository;

    this.programRepository = programRepository;
    this.contractStatusRepository = contractStatusRepository;
    this.managerRepository = managerRepository;
    this.coordinatorRepository = coordinatorRepository;
    this.groupRepository = groupRepository;
  }

  @Override
  protected void decorateDto(ContractRequest dto, Contract entity) {

    super.decorateDto(dto, entity);
  }

  @Override
  protected void decorateEntity(Contract entity, ContractRequest dto) {
    Optional<LegalEntity> legalEntity =
      legalEntityRepository.findByIdCode(dto.getLegalEntityCode());
    LegalEntity saveLegalEntity = legalEntity.orElseThrow(() -> new Error());
    Optional<PersonalCard> personalCard =
      personalCardRepository.findByIdCode(dto.getPersonalCardCode());
    PersonalCard savePersonalCard = personalCard.orElseThrow(()->new Error());
    Optional<Program> program =
      programRepository.findByProgram(dto.getProgramName());
    Program saveProgram = program.orElseThrow(()->new Error());
    Optional<ContractStatus> findContractStatus =
      contractStatusRepository.findByStatus(dto.getContractStatus());
    ContractStatus saveContractStatus = findContractStatus.orElseThrow(()->new Error());
    Optional<Manager> findManager =
      managerRepository.findByFullName(dto.getResponsibleManagerFullName());
    Manager saveManager = findManager.orElseThrow(()->new Error());
    Optional<Coordinator> findCoordinator =
      coordinatorRepository.findByFullName(dto.getCoordinatorFullName());
    Coordinator saveCoordinator = findCoordinator.orElseThrow(()->new Error());
Optional<Group> findGroup =
      groupRepository.findByGroupName(dto.getGroupName());
    Group saveGroup = findGroup.orElseThrow(()->new Error());

    entity.setLegalEntity(saveLegalEntity);
    entity.setPersonalCard(savePersonalCard);
    entity.setProgram(saveProgram);
    entity.setContractStatus(saveContractStatus);
    entity.setManager(saveManager);
    entity.setCoordinator(saveCoordinator);
    entity.setGroup(saveGroup);
    super.decorateEntity(entity, dto);
  }
}