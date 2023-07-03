package com.danit.erp.service.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Mentor;
import com.danit.erp.exception.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.roles.MentorRepository;
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
public class MentorService implements BaseService<Mentor,Integer> {
  private final MentorRepository mentorRepository;

  @Override
  public List<Mentor> findAll() {
    return mentorRepository.findByDeletedFalse();
  }

  @Override
  public Page<Mentor> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return mentorRepository.findByDeletedFalse(pageable);
  }

  @Override
  public Mentor findById(Integer userId) {
    return mentorRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("Ментора"));
  }


  @Override
  public Mentor create(Mentor obj) {
    Mentor mentor = Mentor.builder().fullName(obj.getFullName()).build();
    return mentorRepository.save(mentor);
  }

  @Override
  public void update(Mentor obj) {
    Mentor findMentor = mentorRepository.findByIdAndDeletedFalse(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("Ментора"));

    Mentor mentor = Mentor.builder().id(findMentor.getId()).fullName(obj.getFullName()).build();
    mentorRepository.save(mentor);
  }

  @Override
  public void delete(Integer userId) {
    Mentor mentor =
      mentorRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Ментора"));

    mentor.setDeleted(true);
    mentorRepository.save(mentor);

  }
}
