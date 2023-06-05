package com.danit.erp.dto.personal_card;

import com.danit.erp.domain.dictionary.Education;
import com.danit.erp.domain.dictionary.Email;
import com.danit.erp.domain.dictionary.Profession;
import com.danit.erp.domain.dictionary.Role;
import com.danit.erp.domain.dictionary.University;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PersonalCardResponse {
  private Long id;
  private String password;
  private String surname;
  private String name;
  private String secondName;

  private LocalDateTime dateOfBirth;
  private String idCode;
  private String passportData;
  private String linkToCRM;
  private Profession initialProfession;
  private University university;
  private Role role;
  private Education educationSpecialization;

  private Email email;
}
