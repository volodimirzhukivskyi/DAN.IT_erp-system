package com.danit.erp.facade.contract;

import com.danit.erp.domain.contract.Contract;
import com.danit.erp.domain.personal_card.PersonalCard;
import com.danit.erp.dto.contract.ContractResponse;
import com.danit.erp.facade.GeneralFacade;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class ContractResponseMapper extends GeneralFacade<Contract, ContractResponse> {
  public ContractResponseMapper() {
    super(Contract.class, ContractResponse.class);
  }

  @Override
  protected void decorateDto(ContractResponse dto, Contract entity) {

    PersonalCard clientCard = entity.getPersonalCard();
    String clientName =
      String.format("%s %s %s", clientCard.getSecondName(), clientCard.getSurname(),
        clientCard.getName());
    //TODO винести в окрему функцію потрібна в різних місцях додатка.
    LocalDateTime contractDate = entity.getContractDate();
    LocalDateTime startGropDate = entity.getGroup().getStartDate();
    DateTimeFormatter contractFormatter =
      DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String contractDateString = contractDate.format(contractFormatter);
    String startDateString=startGropDate.format(contractFormatter);
    dto.setLegalEntity(entity.getLegalEntity().getRepresentedBy());
    dto.setClientName(clientName);
    dto.setContractDate(contractDateString);
    dto.setProgram(entity.getProgram().getProgram());
    dto.setProgramHours(entity.getProgram().getProgramHours());
    dto.setResponsibleManager(entity.getManager().getFullName());
    dto.setCoordinator(entity.getCoordinator().getFullName());
    dto.setStatus(entity.getContractStatus().getStatus());
    dto.setGroup(entity.getGroup().getGroupName());
    dto.setStartDate(startDateString);
    super.decorateDto(dto, entity);
  }

  @Override
  public void decorateEntity(Contract entity, ContractResponse dto) {


  }
}