package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Education;
import com.danit.erp.repository.dictionary.EducationRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EducationService implements BaseService<Education> {
  private final EducationRepository educationRepository;

  @Override
  public List<Education> findAll() {
    return educationRepository.findByDeletedFalse();
  }

  @Override
  public List<Education> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public Education findById(Long userId) {
    return educationRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку
  }


  @Override
  public Education create(Education obj) {
    Education education = Education.builder().specialization(obj.getSpecialization()).build();
    return educationRepository.save(education);
  }

  @Override
  public void update(Education obj) {
    Education findEducation =
      educationRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new Error());

    Education education =
      Education.builder().id(findEducation.getId()).specialization(obj.getSpecialization()).build();
    educationRepository.save(education);
  }

  @Override
  public void delete(Long userId) {
    Education education =
      educationRepository.findById(userId).orElseThrow(() -> new Error());

    //TODO зробити помилку
    education.setDeleted(true);
    educationRepository.save(education);

  }
}
