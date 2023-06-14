package com.danit.erp.facade.card.personal_card;

import com.danit.erp.domain.dictionary.Education;
import com.danit.erp.domain.dictionary.Email;
import com.danit.erp.domain.dictionary.Profession;
import com.danit.erp.domain.dictionary.roles.Role;
import com.danit.erp.domain.dictionary.University;
import com.danit.erp.domain.card.personal_card.PersonalCard;
import com.danit.erp.dto.card.personal_card.PersonalCardRequest;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.repository.dictionary.EducationRepository;
import com.danit.erp.repository.dictionary.EmailRepository;
import com.danit.erp.repository.dictionary.ProfessionRepository;
import com.danit.erp.repository.dictionary.roles.RoleRepository;
import com.danit.erp.repository.dictionary.UniversityRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service

public class PersonalCardRequestMapper extends GeneralFacade<PersonalCard, PersonalCardRequest> {

  private final EmailRepository emailRepository;
  private final UniversityRepository universityRepository;
  private final ProfessionRepository professionRepository;
  private final RoleRepository roleRepository;
  private final EducationRepository educationRepository;

  public PersonalCardRequestMapper(
    EmailRepository emailRepository, UniversityRepository universityRepository,
    ProfessionRepository professionRepository, RoleRepository roleRepository,
    EducationRepository educationRepository) {
    super(PersonalCard.class, PersonalCardRequest.class);

    this.emailRepository = emailRepository;
    this.universityRepository = universityRepository;
    this.professionRepository = professionRepository;
    this.roleRepository = roleRepository;
    this.educationRepository = educationRepository;
  }

  @Override
  protected void decorateDto(PersonalCardRequest dto, PersonalCard entity) {

    super.decorateDto(dto, entity);
  }

  @Override
  protected void decorateEntity(PersonalCard entity, PersonalCardRequest dto) {

    Email findEmail = emailRepository.findByIdCode(dto.getIdCode()).orElse(null);
    if (dto.getEmail() != null && findEmail == null) {
      findEmail = emailRepository.save(new Email(dto.getIdCode(), dto.getEmail()));
    }


    University findUniversity =
      universityRepository.findByName(dto.getUniversityName()).orElse(null);
    if (dto.getUniversityName() != null && findUniversity == null) {
      findUniversity =
        universityRepository.save(University.builder().name(dto.getUniversityName()).build());
    }
    Profession findProfession =
      professionRepository.findByName(dto.getInitialProfession()).orElse(null);
    if (dto.getInitialProfession() != null && findProfession == null) {
      findProfession =
        professionRepository.save(Profession.builder().name(dto.getInitialProfession()).build());
    }
    Education findSpecialization =
      educationRepository.findBySpecialization(dto.getSpecializationName()).orElse(null);

    if (dto.getSpecializationName() != null && findSpecialization == null) {
      findSpecialization =
        educationRepository.save(Education.builder().specialization(dto.getSpecializationName()).build());
    }
    Role findRole = roleRepository.findByRole(dto.getRoleName()).orElseThrow(() -> new Error());

    entity.setEmail(findEmail);
    entity.setUniversity(findUniversity);
    entity.setInitialProfession(findProfession);
    entity.setDateOfBirth(LocalDateTime.parse(dto.getDateOfBirth()));
    entity.setEducation(findSpecialization);
    entity.setRole(findRole);

    super.decorateEntity(entity, dto);
  }
}