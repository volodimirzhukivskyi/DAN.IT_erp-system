package com.danit.erp.service.card;

import com.danit.erp.domain.card.personal_card.PersonalCard;
import com.danit.erp.dto.card.personal_card.PersonalCardRequest;
import com.danit.erp.dto.card.personal_card.PersonalCardResponse;
import com.danit.erp.facade.card.personal_card.PersonalCardRequestMapper;
import com.danit.erp.facade.card.personal_card.PersonalCardResponseMapper;
import com.danit.erp.repository.card.PersonalCardRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonalCardService implements BaseService<PersonalCardResponse> {
  private final PersonalCardRepository personalCardRepository;

  private final PersonalCardResponseMapper personalCardResponseMapper;
  private final PersonalCardRequestMapper personalCardRequestMapper;

  @Override
  public List<PersonalCardResponse> findAll() {
    List<PersonalCard> allCards = personalCardRepository.findAll();
    return allCards.stream().map(personalCardResponseMapper::convertToDto).collect(Collectors.toList());

  }

  @Override
  public List<PersonalCardResponse> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public PersonalCardResponse findById(Long userId) {
    PersonalCard findCart = personalCardRepository.findById(userId).orElseThrow(() -> new Error());

    return personalCardResponseMapper.convertToDto(findCart);
    //TODO зробити помилку
  }

  @Override
  public void update(PersonalCardResponse obj) {

  }

  @Override
  public PersonalCardResponse create(PersonalCardResponse obj) {
    return null;
  }

  public PersonalCardResponse create(PersonalCardRequest objDto) {
  PersonalCard obj =personalCardRequestMapper.convertToEntity(objDto);
    PersonalCard personalCard =
      PersonalCard.builder().name(obj.getName()).secondName(obj.getSecondName())
        .surname(obj.getSurname()).linkToCRM(obj.getLinkToCRM()).passportData(obj.getPassportData())
        .email(obj.getEmail())
        .password(obj.getPassword())
        .idCode(obj.getIdCode())
        .university(obj.getUniversity())
        .role(obj.getRole())
        .education(obj.getEducation())
        .initialProfession(obj.getInitialProfession())
          .dateOfBirth(obj.getDateOfBirth())
        .build();
    PersonalCard saveCard = personalCardRepository.save(personalCard);

    return personalCardResponseMapper.convertToDto(saveCard);
  }

  public void update(PersonalCardRequest objDto) {
    PersonalCard obj =personalCardRequestMapper.convertToEntity(objDto);

    PersonalCard findCart =
      personalCardRepository.findById(obj.getId()).orElseThrow(() -> new Error());

    PersonalCard personalCard = PersonalCard.builder().id(findCart.getId()).name(obj.getName()).secondName(obj.getSecondName())
      .surname(obj.getSurname()).linkToCRM(obj.getLinkToCRM()).passportData(obj.getPassportData())
      .email(obj.getEmail()).password(obj.getPassword())
      .idCode(obj.getIdCode())
      .university(obj.getUniversity())
      .role(obj.getRole())
      .education(obj.getEducation())
      .initialProfession(obj.getInitialProfession())
      .dateOfBirth(obj.getDateOfBirth()).build();
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
