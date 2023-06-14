package com.danit.erp.facade.card.contract;

import com.danit.erp.domain.card.contract.Contract;
import com.danit.erp.dto.card.contract.ContractResponse;
import com.danit.erp.dto.card.contract.PageContractResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PageContractResponseMapper {
  private final ContractResponseMapper contractResponseMapper;

  public PageContractResponseMapper(ContractResponseMapper contractResponseMapper) {

    this.contractResponseMapper = contractResponseMapper;
  }

  public PageContractResponse convertToDto(Page<Contract> entity) {
    PageContractResponse dto = new PageContractResponse();

    dto.setTotalPages(entity.getTotalPages());
    dto.setTotalElements(entity.getTotalElements());
    List<Contract> contracts = entity.getContent();
    if (contracts.size() > 0) {
      List<ContractResponse> contractResponses =
        contracts.stream().map(contractResponseMapper::convertToDto).toList();
      dto.setContent(contractResponses);
    }
    return dto;
  }


}