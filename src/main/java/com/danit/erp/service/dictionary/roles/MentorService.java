package com.danit.erp.service.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Mentor;
import com.danit.erp.repository.dictionary.roles.MentorRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MentorService implements BaseService<Mentor> {
  private final MentorRepository mentorRepository;

  @Override
  public List<Mentor> findAll() {
    return mentorRepository.findByDeletedFalse();
  }

  @Override
  public List<Mentor> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public Mentor findById(Long userId) {
    return mentorRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку
  }


  @Override
  public Mentor create(Mentor obj) {
    Mentor mentor = Mentor.builder().fullName(obj.getFullName()).build();
    return mentorRepository.save(mentor);
  }

  @Override
  public void update(Mentor obj) {
    Mentor findMentor =
      mentorRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new Error());

    Mentor mentor =
      Mentor.builder().id(findMentor.getId()).fullName(obj.getFullName()).build();
    mentorRepository.save(mentor);
  }

  @Override
  public void delete(Long userId) {
    Mentor mentor =
      mentorRepository.findById(userId).orElseThrow(() -> new Error());

    //TODO зробити помилку
    mentor.setDeleted(true);
    mentorRepository.save(mentor);

  }
}
