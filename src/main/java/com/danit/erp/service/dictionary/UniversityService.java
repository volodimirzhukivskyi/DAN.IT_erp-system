package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.University;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.UniversityRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UniversityService implements BaseService<University> {
  private final UniversityRepository universityRepository;

  @Override
  public List<University> findAll() {
    return universityRepository.findByDeletedFalse();
  }

  @Override
  public List<University> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public University findById(Long userId) {
    return universityRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new CouldNotFindException("Юніверситети"));
  }


  @Override
  public University create(University obj) {
    University university = University.builder().name(obj.getName()).build();
    return universityRepository.save(university);
  }

  @Override
  public void update(University obj) {
    University findUniversity =
      universityRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new CouldNotFindException("Юніверситети"));

    University university =
      University.builder().id(findUniversity.getId()).name(obj.getName()).build();
    universityRepository.save(university);
  }

  @Override
  public void delete(Long userId) {
    University university =
      universityRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Юніверситети"));

    university.setDeleted(true);
    universityRepository.save(university);

  }
}
