package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Profession;
import com.danit.erp.exception.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.ProfessionRepository;
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
public class ProfessionService implements BaseService<Profession,Short> {
  private final ProfessionRepository professionRepository;

  @Override
  public List<Profession> findAll() {
    return professionRepository.findByDeletedFalse();
  }

  @Override
  public Page<Profession> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return professionRepository.findByDeletedFalse(pageable);
  }

  @Override
  public Profession findById(Short userId) {
    return professionRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("Професії"));
  }


  @Override
  public Profession create(Profession obj) {
    Profession profession = Profession.builder().name(obj.getName()).build();
    return professionRepository.save(profession);
  }

  @Override
  public void update(Profession obj) {
    Profession findProfession = professionRepository.findByIdAndDeletedFalse(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("Професії"));

    Profession profession =
      Profession.builder().id(findProfession.getId()).name(obj.getName()).build();
    professionRepository.save(profession);
  }

  @Override
  public void delete(Short userId) {
    Profession profession = professionRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("Професії"));

    profession.setDeleted(true);
    professionRepository.save(profession);

  }
}
