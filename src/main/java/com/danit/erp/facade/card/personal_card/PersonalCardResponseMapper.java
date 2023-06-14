package com.danit.erp.facade.card.personal_card;

import com.danit.erp.domain.card.personal_card.PersonalCard;
import com.danit.erp.dto.card.personal_card.PersonalCardResponse;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.utils.Helper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class PersonalCardResponseMapper extends GeneralFacade<PersonalCard, PersonalCardResponse> {

  public PersonalCardResponseMapper(
  ) {
    super(PersonalCard.class, PersonalCardResponse.class);
  }

  @Override
  protected void decorateDto(PersonalCardResponse dto, PersonalCard entity) {
    String saveUniversityName = entity.getUniversity().getName();
    LocalDateTime dateBirthday = entity.getDateOfBirth();
    String saveInitialProfession = entity.getInitialProfession().getName();
    dto.setUniversity(saveUniversityName);
    dto.setInitialProfession(saveInitialProfession);
    dto.setDateOfBirth(Helper.convertDate(dateBirthday, "dd.MM.yyyy"));
    dto.setEmail(entity.getEmail().getEmail());
    dto.setRole(entity.getRole().getRole());
    dto.setEducationSpecialization(entity.getEducation().getSpecialization());
    super.decorateDto(dto, entity);
  }

  @Override
  public void decorateEntity(PersonalCard entity, PersonalCardResponse dto) {


  }
}