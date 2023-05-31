package com.danit.erp.domain.personalcard;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.dictionary.EducationSpecialization;
import com.danit.erp.domain.dictionary.EmailList;
import com.danit.erp.domain.dictionary.Profession;
import com.danit.erp.domain.dictionary.Role;
import com.danit.erp.domain.dictionary.University;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "personal_cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PersonalCard extends BaseEntity {

  private String password;
  private String surname;
  private String name;
  private String secondName;

  private LocalDateTime dateOfBirth;
  @Column(name = "id_code")
  private String idCode;
  private String passportData;
  @Column(name = "link_to_crm")
  private String linkToCRM;
  @ManyToOne(targetEntity = Profession.class)
  private Profession initialProfession;
  @ManyToOne(targetEntity = University.class,cascade=CascadeType.ALL)
  @JoinColumn(name = "universities_id")
  private University university;
  @ManyToOne(targetEntity = Role.class)
  private Role role;
  @ManyToOne(targetEntity = EducationSpecialization.class)
  private EducationSpecialization educationSpecialization;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "email_id_code")
  private EmailList email;

}
