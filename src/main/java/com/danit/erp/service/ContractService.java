package com.danit.erp.service;

import com.danit.erp.domain.contract.Contract;
import com.danit.erp.dto.contract.ContractRequest;
import com.danit.erp.dto.contract.ContractResponse;
import com.danit.erp.facade.contract.ContractRequestMapper;
import com.danit.erp.facade.contract.ContractResponseMapper;
import com.danit.erp.repository.ContractRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractService implements BaseService<ContractResponse> {
  private final ContractRepository contractRepository;

  private final ContractRequestMapper contractRequestMapper;
  private final ContractResponseMapper contractResponseMapper;

  @Override
  public List<ContractResponse> findAll() {

    List<Contract> allContract = contractRepository.findAll();
    return allContract.stream().map(contractResponseMapper::convertToDto)
      .collect(Collectors.toList());
  }

  @Override
  public List<ContractResponse> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public ContractResponse findById(Long userId) {
    Contract contract = contractRepository.findById(userId).orElseThrow(() -> new Error());
    return contractResponseMapper.convertToDto(contract);

    //TODO зробити помилку або глянути чи вона взагалі потрібна
  }

  @Override
  public void update(ContractResponse obj) {

  }

  @Override
  public ContractResponse create(ContractResponse obj) {
    return null;
  }

  public ContractResponse create(ContractRequest contractRequest) {

   Contract obj =contractRequestMapper.convertToEntity(contractRequest);

    Contract contract =
      Contract.builder().contractNo(obj.getContractNo()).contractDate(obj.getContractDate())
        .coordinator(obj.getCoordinator())
        .personalCard(obj.getPersonalCard())
        .legalEntity(obj.getLegalEntity())
        .program(obj.getProgram())
        .group(obj.getGroup())
        .contractStatus(obj.getContractStatus())
       .manager(obj.getManager()).contractValue(obj.getContractValue())
        .docLink(obj.getDocLink()).build();
    Contract saveContract = contractRepository.save(contract);

    return contractResponseMapper.convertToDto(saveContract);
  }

  public void update(ContractRequest contractRequest) {

    Contract obj = contractRequestMapper.convertToEntity(contractRequest);
    Contract findContract = contractRepository.findById(obj.getId()).orElseThrow(() -> new Error());

    Contract contract = Contract.builder().id(findContract.getId()).contractNo(obj.getContractNo())
      .contractDate(obj.getContractDate()).personalCard(obj.getPersonalCard())
      .manager(obj.getManager()).group(obj.getGroup()).legalEntity(obj.getLegalEntity()).coordinator(obj.getCoordinator())
      .program(obj.getProgram()).contractStatus(obj.getContractStatus())
      .contractValue(obj.getContractValue()).docLink(obj.getDocLink()).build();
    contractRepository.save(contract);
  }

  @Override
  public void delete(Long userId) {
    Contract contract = contractRepository.findById(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку

    contractRepository.delete(contract);
  }
}
