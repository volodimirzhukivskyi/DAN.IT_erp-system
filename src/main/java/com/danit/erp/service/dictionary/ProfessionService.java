package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Profession;
import com.danit.erp.repository.dictionary.ProfessionRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfessionService implements BaseService<Profession> {
  private final ProfessionRepository professionRepository;

  @Override
  public List<Profession> findAll() {
    return professionRepository.findByDeletedFalse();
  }

  @Override
  public List<Profession> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public Profession findById(Long userId) {
    return professionRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку
  }


  @Override
  public Profession create(Profession obj) {
    Profession university = Profession.builder().name(obj.getName()).build();
    return professionRepository.save(university);
  }

  @Override
  public void update(Profession obj) {
    Profession findUniversity =
      professionRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new Error());

    Profession university =
      Profession.builder().id(findUniversity.getId()).name(obj.getName()).build();
    professionRepository.save(university);
  }

  @Override
  public void delete(Long userId) {
    Profession university =
      professionRepository.findById(userId).orElseThrow(() -> new Error());

    //TODO зробити помилку
    university.setDeleted(true);
    professionRepository.save(university);

  }
}
