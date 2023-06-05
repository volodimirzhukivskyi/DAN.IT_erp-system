package com.danit.erp.facade.personal_card;

import com.danit.erp.domain.personal_card.PersonalCard;
import com.danit.erp.dto.personal_card.PersonalCardResponse;
import com.danit.erp.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class PersonalCardResponseMapper extends GeneralFacade<PersonalCard, PersonalCardResponse> {
  public PersonalCardResponseMapper() {
    super(PersonalCard.class, PersonalCardResponse.class);
  }

  @Override
  protected void decorateDto(PersonalCardResponse dto, PersonalCard entity) {


    super.decorateDto(dto, entity);
  }

  @Override
  public void decorateEntity(PersonalCard entity, PersonalCardResponse dto) {


  }
}