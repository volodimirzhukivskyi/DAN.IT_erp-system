package com.danit.erp.domain.card.personal_card;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.dictionary.Education;
import com.danit.erp.domain.dictionary.Email;
import com.danit.erp.domain.dictionary.Profession;
import com.danit.erp.domain.dictionary.University;
import com.danit.erp.domain.role.RoleEnum;
import com.danit.erp.domain.token.Token;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "personal_cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PersonalCard extends BaseEntity<Long> implements UserDetails {
  @Column(nullable = false)
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
  @ManyToOne(targetEntity = Profession.class, cascade = CascadeType.ALL)
  private Profession initialProfession;
  @ManyToOne(targetEntity = University.class, cascade = CascadeType.ALL)
  private University university;
  @Enumerated(EnumType.STRING)
  private RoleEnum role;
  @OneToMany(mappedBy = "personalCard")
  private List<Token> tokens;
  @ManyToOne(targetEntity = Education.class)
  private Education education;

  @OneToOne
  @JoinColumn(name = "email_id", referencedColumnName = "id_code")
  private Email email;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getUsername() {
    return email.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
