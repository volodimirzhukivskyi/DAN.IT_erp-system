package com.danit.erp.auth.reset_password;

import com.danit.erp.domain.card.personal_card.PersonalCard;
import java.util.Calendar;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {
  private final PasswordResetTokenRepository passwordResetTokenRepository;

  public void createPasswordResetTokenForUser(PersonalCard personalCard, String passwordToken) {
    PasswordResetToken passwordResetToken = new PasswordResetToken(passwordToken, personalCard);
    passwordResetTokenRepository.save(passwordResetToken);
  }

  public void deletePasswordResetToken(String theToken) {
    PasswordResetToken token = passwordResetTokenRepository.findByToken(theToken);
    passwordResetTokenRepository.delete(token);
  }
  public String validatePasswordResetToken(String theToken) {
    PasswordResetToken token = passwordResetTokenRepository.findByToken(theToken);
    if (token == null) {
      return "Invalid password reset token";

    }
    Calendar calendar = Calendar.getInstance();
    if ((token.getTokenExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
      return "Link already expired, resend link";
    }
    return "valid";
  }

  public Optional<PersonalCard> findUserByPasswordToken(String passwordToken) {
    return Optional.ofNullable(
      passwordResetTokenRepository.findByToken(passwordToken).getPersonalCard());
  }
}
