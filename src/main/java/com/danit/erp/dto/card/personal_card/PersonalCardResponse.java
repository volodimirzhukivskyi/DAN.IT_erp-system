package com.danit.erp.dto.card.personal_card;

import lombok.Data;

@Data
public class PersonalCardResponse {
  private Long id;
  private String password;
  private String surname;
  private String name;
  private String secondName;

  private String dateOfBirth;
  private String idCode;
  private String passportData;
  private String linkToCRM;
  private String initialProfession;
  private String university;
  private String role;
  private String educationSpecialization;

  private String email;
}
