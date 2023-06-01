package com.danit.erp.service;

import com.danit.erp.domain.dictionary.University;
import com.danit.erp.domain.personalcard.PersonalCard;
import com.danit.erp.repository.PersonalCardRepository;
import com.danit.erp.repository.dictionary.UniversityRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonalCardService implements BaseService<PersonalCard> {
  private final PersonalCardRepository personalCardRepository;
     private final UniversityRepository universityRepository;

  @Override
  public List<PersonalCard> findAll() {
    return personalCardRepository.findAll();
  }

  @Override
  public List<PersonalCard> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public PersonalCard findById(Long userId) {
    return personalCardRepository.findById(userId)
      .orElseThrow(() -> new Error());
    //TODO зробити помилку
  }

  @Override
  public PersonalCard create(PersonalCard obj) {
    //TODO  - логіку перенести в dtoMapper
    Optional<University> university= universityRepository.findByName(obj.getUniversity().getName());
     University saveUniversity = university.orElseGet(obj::getUniversity);


    PersonalCard personalCard= PersonalCard.builder()
      .name(obj.getName())
      .secondName(obj.getSecondName())
      .surname(obj.getSurname())
      .linkToCRM(obj.getLinkToCRM())
      .passportData(obj.getPassportData())
      .dateOfBirth(obj.getDateOfBirth())
      .email(obj.getEmail())
      .password(obj.getPassword())
      .idCode(obj.getIdCode())
      .university(saveUniversity)
      .build();
    return    personalCardRepository.save(personalCard);
  }

  @Override
  public void update(PersonalCard obj) {
 PersonalCard findCart =personalCardRepository.findById(obj.getId())
      .orElseThrow(() -> new Error());

    PersonalCard personalCard= PersonalCard.builder()
       .id(findCart.getId())
       .name(obj.getName())
       .secondName(obj.getSecondName())
       .surname(obj.getSurname())
       .linkToCRM(obj.getLinkToCRM())
       .passportData(obj.getPassportData())
       .dateOfBirth(obj.getDateOfBirth())
       .email(obj.getEmail())
       .password(obj.getPassword())
       .idCode(obj.getIdCode())
      .university(obj.getUniversity())
       .build();
        personalCardRepository.save(personalCard);
  }

  @Override
  public void delete(Long userId) {
    PersonalCard personalCard =
      personalCardRepository.findById(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку

     personalCardRepository.delete(personalCard);
  }
}
