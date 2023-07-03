package com.danit.erp.config.event;

import com.danit.erp.domain.card.personal_card.PersonalCard;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
  private PersonalCard personalCard;
  private String applicationUrl;

  public RegistrationCompleteEvent(PersonalCard personalCard, String applicationUrl) {
    super(personalCard);
    this.personalCard = personalCard;
    this.applicationUrl = applicationUrl;
  }
}