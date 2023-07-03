package com.danit.erp.auth.reset_password;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.card.personal_card.PersonalCard;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.Calendar;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PasswordResetToken extends BaseEntity<Long> {
  private static final int EXPIRATION_TIME = 15;
  private String token;
  private Date expirationTime;
  @OneToOne
  @JoinColumn(name = "personal_card_id")
  private PersonalCard personalCard;

  public PasswordResetToken(String token, PersonalCard personalCard) {
    super();
    this.token = token;
    this.personalCard = personalCard;
    this.expirationTime = this.getTokenExpirationTime();
  }



  public Date getTokenExpirationTime() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(new Date().getTime());
    calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
    return new Date(calendar.getTime().getTime());
  }
}

