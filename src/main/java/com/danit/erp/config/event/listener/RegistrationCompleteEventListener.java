package com.danit.erp.config.event.listener;


import com.danit.erp.domain.card.personal_card.PersonalCard;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener {

  private final JavaMailSender mailSender;


  public void sendPasswordResetVerificationEmail(String url, PersonalCard personalCard)
    throws MessagingException, UnsupportedEncodingException {
    String subject = "Password Reset Request Verification";
    String senderName = "User Registration Portal Service";
    String mailContent = "<p> Hi, " + personalCard.getName() + ", </p>"
      + "<p><b>You recently requested to reset your password.</b></p>" + ""
      + "Please, follow the link below to complete the action: </p>" + "<a href=\"" + url
      + "\">Reset password</a>" + "<p><i> If you didn’t request to change your password, you don’t "
      + "need to do anything, and can safely ignore this email.</p>";
    MimeMessage message = mailSender.createMimeMessage();
    var messageHelper = new MimeMessageHelper(message);
    messageHelper.setFrom("danittest2@gmail.com", senderName);
    messageHelper.setTo(personalCard.getEmail().getEmail());
    messageHelper.setSubject(subject);
    messageHelper.setText(mailContent, true);


    mailSender.send(message);
  }
}