package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Education;
import com.danit.erp.exception.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.EducationRepository;
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
public class EducationService implements BaseService<Education,Short> {
  private final EducationRepository educationRepository;

  @Override
  public List<Education> findAll() {
    return educationRepository.findByDeletedFalse();
  }



  @Override
  public Page<Education> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return educationRepository.findByDeletedFalse(pageable);
  }

  @Override
  public Education findById(Short userId) {
    return educationRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("The specializations"));

  }


  @Override
  public Education create(Education obj) {
    Education education = Education.builder().specialization(obj.getSpecialization()).build();
    return educationRepository.save(education);
  }

  @Override
  public void update(Education obj) {
    Education findEducation = educationRepository.findByIdAndDeletedFalse(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("The specializations"));

    Education education =
      Education.builder().id(findEducation.getId()).specialization(obj.getSpecialization()).build();
    educationRepository.save(education);
  }

  @Override
  public void delete(Short userId) {
    Education education = educationRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("The specializations"));

    education.setDeleted(true);
    educationRepository.save(education);

  }
}
