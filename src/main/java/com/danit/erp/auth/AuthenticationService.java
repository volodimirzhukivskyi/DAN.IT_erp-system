package com.danit.erp.auth;

import com.danit.erp.domain.card.personal_card.PersonalCard;
import com.danit.erp.dto.card.personal_card.PersonalCardRequest;
import com.danit.erp.repository.card.PersonalCardRepository;
import com.danit.erp.service.JwtService;
import com.danit.erp.service.card.PersonalCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final PersonalCardRepository personalCardRepository;
  private final PersonalCardService personalCardService;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(PersonalCardRequest request) {

    PersonalCard personalCard = personalCardService.createEntity(request);
    var jwtToken = jwtService.generateToken(personalCard);
    return AuthenticationResponse.builder().token(jwtToken).build();

  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var personalCard = personalCardRepository.findByEmail_Email(request.getEmail())
      .orElseThrow(() -> new Error());
    //TODO зробити помилку читабельну
    var jwtToken = jwtService.generateToken(personalCard);
    return AuthenticationResponse.builder().token(jwtToken).build();

  }
}
