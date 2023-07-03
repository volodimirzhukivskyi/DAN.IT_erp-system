package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.LegalEntity;
import com.danit.erp.exception.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.LegalEntityRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LegalEntityService implements BaseService<LegalEntity,Integer> {
  private final LegalEntityRepository legalEntityRepository;

  @Override
  public List<LegalEntity> findAll() {
    return legalEntityRepository.findByDeletedFalse();
  }

  @Override
  public Page<LegalEntity> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return legalEntityRepository.findByDeletedFalse(pageable);

  }

  @Override
  public LegalEntity findById(Integer userId) {
    return legalEntityRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("Юридичної особи"));
  }


  @Override
  public LegalEntity create(LegalEntity obj) {
    LegalEntity legalEntity =
      LegalEntity.builder().statuteEntity(obj.getStatuteEntity()).bank(obj.getBank())
        .IBAN(obj.getIBAN()).legalEntityName(obj.getLegalEntityName()).idCode(obj.getIdCode())
        .legalAddress(obj.getLegalAddress()).representedBy(obj.getRepresentedBy()).build();
    return legalEntityRepository.save(legalEntity);
  }

  @Override
  public void update(LegalEntity obj) {
    LegalEntity legalEntity = legalEntityRepository.findByIdAndDeletedFalse(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("Юридичної особи"));

    LegalEntity legalEntityUpdate =
      LegalEntity.builder().id(legalEntity.getId()).statuteEntity(obj.getStatuteEntity())
        .bank(obj.getBank()).IBAN(obj.getIBAN()).legalEntityName(obj.getLegalEntityName())
        .idCode(obj.getIdCode()).legalAddress(obj.getLegalAddress())
        .representedBy(obj.getRepresentedBy()).build();
    legalEntityRepository.save(legalEntityUpdate);
  }

  @Override
  public void delete(Integer userId) {
    LegalEntity legalEntity = legalEntityRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("Юридичної особи"));

    legalEntity.setDeleted(true);
    legalEntityRepository.save(legalEntity);

  }
}
