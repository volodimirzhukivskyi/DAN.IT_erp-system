package com.danit.erp.auth.event.listener;


import com.danit.erp.auth.event.RegistrationCompleteEvent;
import com.danit.erp.domain.card.personal_card.PersonalCard;
import com.danit.erp.service.card.PersonalCardService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener
  implements ApplicationListener<RegistrationCompleteEvent> {
  private final PersonalCardService personalCardService;
  private final JavaMailSender mailSender;
  private PersonalCard personalCard;

  @Override
  public void onApplicationEvent(RegistrationCompleteEvent event) {
    // 1. Get the newly registered user
    personalCard = event.getPersonalCard();
    //2. Create a verification token for the user
    String verificationToken = UUID.randomUUID().toString();
    //3. Save the verification token for the user
//    personalCardService.saveUserVerificationToken(personalCard, verificationToken);
    //4 Build the verification url to be sent to the user
    String url = event.getApplicationUrl() + "/register/verifyEmail?token=" + verificationToken;
    //5. Send the email.
    try {
      sendVerificationEmail(url);
    } catch (MessagingException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    log.info("Click the link to verify your registration :  {}", url);
  }

  public void sendVerificationEmail(String url)
    throws MessagingException, UnsupportedEncodingException {
    String subject = "Email Verification";
    String senderName = "User Registration Portal Service";
    String mailContent =
      "<p> Hi, " + personalCard.getName() + ", </p>" + "<p>Thank you for registering with us," + ""
        + "Please, follow the link below to complete your registration.</p>" + "<a href=\"" + url
        + "\">Verify your email to activate your account</a>"
        + "<p> Thank you <br> Users Registration Portal Service";
    MimeMessage message = mailSender.createMimeMessage();
    var messageHelper = new MimeMessageHelper(message);
    messageHelper.setFrom("dailycodework@gmail.com", senderName);
    messageHelper.setTo(personalCard.getEmail().getEmail());
    messageHelper.setSubject(subject);
    messageHelper.setText(mailContent, true);
    mailSender.send(message);
  }

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