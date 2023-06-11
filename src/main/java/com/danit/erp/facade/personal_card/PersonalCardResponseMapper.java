package com.danit.erp.facade.personal_card;

import com.danit.erp.domain.personal_card.PersonalCard;
import com.danit.erp.dto.personal_card.PersonalCardResponse;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.repository.dictionary.ProfessionRepository;
import com.danit.erp.repository.dictionary.UniversityRepository;
import com.danit.erp.utils.Helper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class PersonalCardResponseMapper extends GeneralFacade<PersonalCard, PersonalCardResponse> {
  private final UniversityRepository universityRepository;
  private final ProfessionRepository professionRepository;

  public PersonalCardResponseMapper(
    UniversityRepository universityRepository, ProfessionRepository professionRepository) {
    super(PersonalCard.class, PersonalCardResponse.class);
    this.universityRepository = universityRepository;
    this.professionRepository = professionRepository;
  }

  @Override
  protected void decorateDto(PersonalCardResponse dto, PersonalCard entity) {
    String saveUniversityName = entity.getUniversity().getName();
    LocalDateTime dateBirthday= entity.getDateOfBirth();
    String saveInitialProfession = entity.getInitialProfession().getName();
    dto.setUniversity(saveUniversityName);
    dto.setInitialProfession(saveInitialProfession);
    dto.setDateOfBirth(Helper.convertDate(dateBirthday,"dd.MM.yyyy"));
    dto.setEmail(entity.getEmail().getEmail());
    dto.setRole(entity.getRole().getRole());
    dto.setEducationSpecialization(entity.getEducation().getSpecialization());
    super.decorateDto(dto, entity);
  }

  @Override
  public void decorateEntity(PersonalCard entity, PersonalCardResponse dto) {


  }
}