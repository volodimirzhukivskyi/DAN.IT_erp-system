package com.danit.erp.domain.card.personal_card;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.dictionary.Education;
import com.danit.erp.domain.dictionary.Email;
import com.danit.erp.domain.dictionary.Profession;
import com.danit.erp.domain.dictionary.roles.Role;
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
  @ManyToOne(targetEntity = Profession.class,cascade=CascadeType.ALL)
  private Profession initialProfession;
  @ManyToOne(targetEntity = University.class,cascade=CascadeType.ALL)
  private University university;
  @ManyToOne(targetEntity = Role.class,cascade=CascadeType.PERSIST)
  private Role role;
  @ManyToOne(targetEntity = Education.class)
  private Education education;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "email_id", referencedColumnName = "id_code")
  private Email email;


}
