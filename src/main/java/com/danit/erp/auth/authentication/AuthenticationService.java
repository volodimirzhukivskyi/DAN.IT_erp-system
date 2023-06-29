package com.danit.erp.auth.authentication;

import com.danit.erp.auth.event.listener.RegistrationCompleteEventListener;
import com.danit.erp.auth.reset_password.PasswordResetRequest;
import com.danit.erp.auth.reset_password.PasswordResetTokenService;
import com.danit.erp.domain.card.personal_card.PersonalCard;
import com.danit.erp.dto.card.personal_card.PersonalCardRequest;
import com.danit.erp.repository.card.PersonalCardRepository;
import com.danit.erp.service.JwtService;
import com.danit.erp.service.card.PersonalCardService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final PersonalCardRepository personalCardRepository;
  private final PersonalCardService personalCardService;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final PasswordResetTokenService passwordResetTokenService;
  private final RegistrationCompleteEventListener eventListener;

  public AuthenticationResponse register(PersonalCardRequest request) {

    PersonalCard personalCard = personalCardService.createEntity(request);
    var jwtToken = jwtService.generateToken(personalCard);
    return AuthenticationResponse.builder().token(jwtToken).build();

  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var personalCard =
      personalCardRepository.findByEmail_Email(request.getEmail()).orElseThrow(() -> new Error());
    //TODO зробити помилку читабельну
    var jwtToken = jwtService.generateToken(personalCard);
    return AuthenticationResponse.builder().token(jwtToken).build();

  }

  public String resetPasswordRequest(
    PasswordResetRequest passwordRestRequest, HttpServletRequest request)
    throws MessagingException, UnsupportedEncodingException {
    Optional<PersonalCard> personalCard =
      personalCardRepository.findByEmail_Email(passwordRestRequest.getEmail());
    String passwordResetUrl = "";
    if (personalCard.isPresent()) {
      String passwordResetToken = UUID.randomUUID().toString();
      passwordResetTokenService.createPasswordResetTokenForUser(personalCard.get(),
        passwordResetToken);
      passwordResetUrl =
        passwordResetEmailLink(personalCard.get(), applicationUrl(request), passwordResetToken);

    }
    return passwordResetUrl;


  }

  public String resetPassword(
    PasswordResetRequest passwordResetRequest, String passwordResetToken) {
    String tokenValidationResult =
      passwordResetTokenService.validatePasswordResetToken(passwordResetToken);
    if (! tokenValidationResult.equalsIgnoreCase("valid")) {
      passwordResetTokenService.deletePasswordResetToken(passwordResetToken);
      return "Invalid password reset token";
    }
    PersonalCard personalCard =
      passwordResetTokenService.findUserByPasswordToken(passwordResetToken).get();
    if (personalCard != null) {
      resetPersonalCardPassword(personalCard, passwordResetRequest.getNewPassword());
      passwordResetTokenService.deletePasswordResetToken(passwordResetToken);
      return "Password has been reset successfully";
    }
    return "Invalid password reset token";

  }


  private String passwordResetEmailLink(
    PersonalCard personalCard, String applicationUrl, String passwordResetToken)
    throws MessagingException, UnsupportedEncodingException {
    String url = applicationUrl + "/api/v0/auth/reset-password?token=" + passwordResetToken;
    eventListener.sendPasswordResetVerificationEmail(url, personalCard);
    log.info("Click the link to reset your password : {}", url);
    return url;
  }

  public String applicationUrl(HttpServletRequest request) {
    return "http://" + request.getServerName() + ":" + request.getServerPort()
      + request.getContextPath();
  }


  public void resetPersonalCardPassword(PersonalCard personalCard, String newPassword) {
    personalCard.setPassword(passwordEncoder.encode(newPassword));
    personalCardRepository.save(personalCard);
  }
}
