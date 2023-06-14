package com.danit.erp.facade.card.contract;

import com.danit.erp.domain.card.contract.Contract;
import com.danit.erp.dto.card.contract.ContractResponse;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.utils.Helper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class ContractResponseMapper extends GeneralFacade<Contract, ContractResponse> {
  public ContractResponseMapper() {
    super(Contract.class, ContractResponse.class);
  }

  @Override
  protected void decorateDto(ContractResponse dto, Contract entity) {

    String clientName = Helper.getFullName(entity.getPersonalCard());

    LocalDateTime contractDate = entity.getContractDate();
    LocalDateTime startGroupDate = entity.getGroup().getStartDate();

    dto.setLegalEntity(entity.getLegalEntity().getRepresentedBy());
    dto.setClientName(clientName);
    dto.setContractDate(Helper.convertDate(contractDate,"dd.MM.yyyy"));
    dto.setProgram(entity.getProgram().getProgram());
    dto.setProgramHours(entity.getProgram().getProgramHours());
    dto.setResponsibleManager(entity.getManager().getFullName());
    dto.setCoordinator(entity.getCoordinator().getFullName());
    dto.setStatus(entity.getContractStatus().getStatus());
    dto.setGroup(entity.getGroup().getGroupName());
    dto.setStartDate(Helper.convertDate(startGroupDate,"dd.MM.yyyy"));
    super.decorateDto(dto, entity);
  }

  @Override
  public void decorateEntity(Contract entity, ContractResponse dto) {


  }
}