package com.danit.erp.auth;

import com.danit.erp.dto.card.personal_card.PersonalCardRequest;
import com.danit.erp.facade.card.personal_card.PersonalCardRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService service;
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
    @RequestBody PersonalCardRequest request
  ){
    return ResponseEntity.ok(service.register(request));
    }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
    @RequestBody AuthenticationRequest request
  ){
    return ResponseEntity.ok(service.authenticate(request));
  }

}