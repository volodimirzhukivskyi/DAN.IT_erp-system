package com.danit.erp.service.card;

import com.danit.erp.domain.card.contract.Contract;
import com.danit.erp.dto.card.contract.ContractRequest;
import com.danit.erp.dto.card.contract.ContractResponse;
import com.danit.erp.dto.card.contract.PageContractResponse;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.facade.card.contract.ContractRequestMapper;
import com.danit.erp.facade.card.contract.ContractResponseMapper;
import com.danit.erp.facade.card.contract.PageContractResponseMapper;
import com.danit.erp.repository.card.ContractRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractService implements BaseService<ContractResponse> {
  private final ContractRepository contractRepository;

  private final ContractRequestMapper contractRequestMapper;
  private final ContractResponseMapper contractResponseMapper;
  private final PageContractResponseMapper pageContractResponseMapper;

  @Override
  public List<ContractResponse> findAll() {

    List<Contract> allContract = contractRepository.findAll();
    return allContract.stream().map(contractResponseMapper::convertToDto)
      .collect(Collectors.toList());
  }

  public PageContractResponse getAllPage(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    Page<Contract> all = contractRepository.findAll(pageable);
    return pageContractResponseMapper.convertToDto(all);
  }

  @Override
  public ContractResponse findById(Long userId) {
    Contract contract =
      contractRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Контракту"));
    return contractResponseMapper.convertToDto(contract);


  }

  @Override
  public void update(ContractResponse obj) {

  }

  @Override
  public ContractResponse create(ContractResponse obj) {
    return null;
  }

  public ContractResponse create(ContractRequest contractRequest) {

    Contract obj = contractRequestMapper.convertToEntity(contractRequest);

    Contract contract =
      Contract.builder().contractNo(obj.getContractNo()).contractDate(obj.getContractDate())
        .coordinator(obj.getCoordinator()).personalCard(obj.getPersonalCard())
        .legalEntity(obj.getLegalEntity()).program(obj.getProgram()).group(obj.getGroup())
        .contractStatus(obj.getContractStatus()).manager(obj.getManager())
        .contractValue(obj.getContractValue()).docLink(obj.getDocLink()).build();
    Contract saveContract = contractRepository.save(contract);

    return contractResponseMapper.convertToDto(saveContract);
  }

  public void update(ContractRequest contractRequest) {

    Contract obj = contractRequestMapper.convertToEntity(contractRequest);
    Contract findContract = contractRepository.findById(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("Контракту"));

    Contract contract = Contract.builder().id(findContract.getId()).contractNo(obj.getContractNo())
      .contractDate(obj.getContractDate()).personalCard(obj.getPersonalCard())
      .manager(obj.getManager()).group(obj.getGroup()).legalEntity(obj.getLegalEntity())
      .coordinator(obj.getCoordinator()).program(obj.getProgram())
      .contractStatus(obj.getContractStatus()).contractValue(obj.getContractValue())
      .docLink(obj.getDocLink()).build();
    contractRepository.save(contract);
  }

  @Override
  public void delete(Long userId) {
    Contract contract =
      contractRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Контракту"));

    contractRepository.delete(contract);
  }
}
