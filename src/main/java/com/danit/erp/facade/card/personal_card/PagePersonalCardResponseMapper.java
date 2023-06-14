package com.danit.erp.facade.card.personal_card;

import com.danit.erp.domain.card.personal_card.PersonalCard;
import com.danit.erp.dto.card.personal_card.PagePersonalCardResponse;
import com.danit.erp.dto.card.personal_card.PersonalCardResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PagePersonalCardResponseMapper {
  private final PersonalCardResponseMapper personalCardResponseMapper;

  public PagePersonalCardResponseMapper(PersonalCardResponseMapper personalCardResponseMapper) {

    this.personalCardResponseMapper = personalCardResponseMapper;
  }

  public PagePersonalCardResponse convertToDto(Page<PersonalCard> entity) {
    PagePersonalCardResponse dto = new PagePersonalCardResponse();

    dto.setTotalPages(entity.getTotalPages());
    dto.setTotalElements(entity.getTotalElements());
    List<PersonalCard> personalCards = entity.getContent();
    if (personalCards.size() > 0) {
      List<PersonalCardResponse> personalCardResponses =
        personalCards.stream().map(personalCardResponseMapper::convertToDto).toList();
      dto.setContent(personalCardResponses);
    }
    return dto;
  }


}