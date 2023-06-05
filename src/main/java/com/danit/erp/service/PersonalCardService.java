package com.danit.erp.service;

import com.danit.erp.domain.dictionary.Profession;
import com.danit.erp.domain.dictionary.University;
import com.danit.erp.domain.personal_card.PersonalCard;
import com.danit.erp.dto.personal_card.PersonalCardResponse;
import com.danit.erp.facade.personal_card.PersonalCardResponseMapper;
import com.danit.erp.repository.PersonalCardRepository;
import com.danit.erp.repository.dictionary.ProfessionRepository;
import com.danit.erp.repository.dictionary.UniversityRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonalCardService implements BaseService<PersonalCardResponse> {
  private final PersonalCardRepository personalCardRepository;
  private final UniversityRepository universityRepository;
  private final ProfessionRepository professionRepository;
  private final PersonalCardResponseMapper personalResponseMapper;

  @Override
  public List<PersonalCardResponse> findAll() {
    List<PersonalCard> allCards = personalCardRepository.findAll();
    return allCards.stream().map(personalResponseMapper::convertToDto).collect(Collectors.toList());

  }

  @Override
  public List<PersonalCardResponse> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public PersonalCardResponse findById(Long userId) {
    PersonalCard findCart = personalCardRepository.findById(userId).orElseThrow(() -> new Error());

    return personalResponseMapper.convertToDto(findCart);
    //TODO зробити помилку
  }

  @Override
  public void update(PersonalCardResponse obj) {

  }

  @Override
  public PersonalCardResponse create(PersonalCardResponse obj) {
    return null;
  }

  public PersonalCardResponse create(PersonalCard obj) {
    //TODO  - логіку перенести в dtoMapper
    Optional<University> university =
      universityRepository.findByName(obj.getUniversity().getName());
    University saveUniversity = university.orElseGet(obj::getUniversity);
    Optional<Profession> profession =
      professionRepository.findByName(obj.getInitialProfession().getName());
    Profession saveInitialProfession = profession.orElseGet(obj::getInitialProfession);

    PersonalCard personalCard =
      PersonalCard.builder().name(obj.getName()).secondName(obj.getSecondName())
        .surname(obj.getSurname()).linkToCRM(obj.getLinkToCRM()).passportData(obj.getPassportData())
        .dateOfBirth(obj.getDateOfBirth()).email(obj.getEmail()).password(obj.getPassword())
        .idCode(obj.getIdCode()).university(saveUniversity).initialProfession(saveInitialProfession)
        .build();
    PersonalCard saveCard = personalCardRepository.save(personalCard);

    return personalResponseMapper.convertToDto(saveCard);
  }

  public void update(PersonalCard obj) {
    PersonalCard findCart =
      personalCardRepository.findById(obj.getId()).orElseThrow(() -> new Error());

    PersonalCard personalCard = PersonalCard.builder().id(findCart.getId()).name(obj.getName())
      .secondName(obj.getSecondName()).surname(obj.getSurname()).linkToCRM(obj.getLinkToCRM())
      .passportData(obj.getPassportData()).dateOfBirth(obj.getDateOfBirth()).email(obj.getEmail())
      .password(obj.getPassword()).idCode(obj.getIdCode()).university(obj.getUniversity())
      .initialProfession(obj.getInitialProfession()).build();
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
