package com.danit.erp.auth.authentication;

import com.danit.erp.auth.reset_password.PasswordResetRequest;
import com.danit.erp.dto.card.personal_card.PersonalCardRequest;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService service;


  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
    @RequestBody PersonalCardRequest request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
    @RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }
  @PostMapping("/refresh-token")
  public void refreshToken(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }
  @PostMapping("/password-reset-request")
  public String resetPasswordRequest(
    @RequestBody PasswordResetRequest passwordRestRequest, final HttpServletRequest request)
    throws MessagingException, UnsupportedEncodingException {

    return service.resetPasswordRequest(passwordRestRequest, request);


  }



  @PostMapping("/reset-password")
  public String resetPassword(
    @RequestBody PasswordResetRequest passwordResetRequest,
    @RequestParam("token") String passwordResetToken) {
    return service.resetPassword(passwordResetRequest, passwordResetToken);

  }


}