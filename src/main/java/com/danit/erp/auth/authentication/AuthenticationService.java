package com.danit.erp.auth.authentication;

import com.danit.erp.auth.event.listener.RegistrationCompleteEventListener;
import com.danit.erp.auth.reset_password.PasswordResetRequest;
import com.danit.erp.auth.reset_password.PasswordResetTokenService;
import com.danit.erp.config.JwtService;
import com.danit.erp.domain.card.personal_card.PersonalCard;
import com.danit.erp.domain.token.Token;
import com.danit.erp.domain.token.TokenType;
import com.danit.erp.dto.card.personal_card.PersonalCardRequest;
import com.danit.erp.repository.card.PersonalCardRepository;
import com.danit.erp.repository.token.TokenRepository;
import com.danit.erp.service.card.PersonalCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
  private final TokenRepository tokenRepository;

  public AuthenticationResponse register(PersonalCardRequest request) {

    PersonalCard personalCard = personalCardService.createEntity(request);

    var jwtToken = jwtService.generateToken(personalCard);
    revokeAllUserTokens(personalCard);
    saveUserToken(personalCard,jwtToken);
    return AuthenticationResponse.builder().accessToken(jwtToken).build();

  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var personalCard =
      personalCardRepository.findByEmail_Email(request.getEmail()).orElseThrow(() -> new Error());
    //TODO зробити помилку читабельну
    var jwtToken = jwtService.generateToken(personalCard);
    var refreshToken = jwtService.generateRefreshToken(personalCard);
    revokeAllUserTokens(personalCard);
    saveUserToken(personalCard,jwtToken);
    return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken)
      .build();

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

  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null || ! authHeader.startsWith("Bearer ")) {
      return;

    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var personalCard = personalCardRepository.findByEmail_Email(userEmail).orElseThrow();
      if (jwtService.isTokenValid(refreshToken, personalCard)) {
        var accessToken = jwtService.generateToken(personalCard);
        revokeAllUserTokens(personalCard);
        saveUserToken(personalCard,accessToken);
        var authResponse =
          AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken)
            .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
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
  private void saveUserToken(PersonalCard personalCard, String jwtToken) {
    var token = Token.builder()
      .personalCard(personalCard)
      .token(jwtToken)
      .tokenType(TokenType.BEARER)
      .expired(false)
      .revoked(false)
      .build();
    tokenRepository.save(token);
  }
  private void revokeAllUserTokens(PersonalCard personalCard){
    var validCardToken = tokenRepository.findAllValidTokensByPersonalCard(personalCard.getId());
    if(validCardToken.isEmpty())
      return;
    validCardToken.forEach(t->{
      t.setExpired(true);
      t.setRevoked(true);
    });
    tokenRepository.saveAll(validCardToken);
  }

  public void resetPersonalCardPassword(PersonalCard personalCard, String newPassword) {
    personalCard.setPassword(passwordEncoder.encode(newPassword));
    personalCardRepository.save(personalCard);
  }


}
