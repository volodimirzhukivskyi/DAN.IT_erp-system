package com.danit.erp.domain.dictionary;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.personalcard.PersonalCard;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "email_lists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailList extends BaseEntity {
  @Column(name = "id_code")
  private String idCode;
  private String email;
  @OneToOne(mappedBy = "email")
  private PersonalCard personalCard;
}
