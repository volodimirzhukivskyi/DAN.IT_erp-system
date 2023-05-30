package com.danit.erp.service;

import com.danit.erp.domain.personalcard.PersonalCard;
import com.danit.erp.repository.PersonalCardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
@RequiredArgsConstructor
public class PersonalCardService implements BaseService<PersonalCard> {
private final PersonalCardRepository personalCardRepository;


  @Override
  public List<PersonalCard> findAll() {
    return personalCardRepository.findAll();
  }

  @Override
  public List<PersonalCard> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public PersonalCard getById(Long userId) {
    return null;
  }

  @Override
  public void update(PersonalCard obj) {

  }

  @Override
  public void create(PersonalCard obj) {

  }

  @Override
  public void delete(Integer id) {

  }
}
