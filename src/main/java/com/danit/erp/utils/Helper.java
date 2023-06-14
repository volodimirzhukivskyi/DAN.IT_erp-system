package com.danit.erp.utils;

import com.danit.erp.domain.card.personal_card.PersonalCard;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
  public static String convertDate(LocalDateTime localDateTime, String pattern) {

    DateTimeFormatter contractFormatter = DateTimeFormatter.ofPattern(pattern);
    return localDateTime.format(contractFormatter);
  }

  public static LocalDateTime convertInLocalDate(String userData) {
    String[] split = userData.split(":");
    int hours = Integer.parseInt(split[0]);
    int minutes = Integer.parseInt(split[1]);
    return LocalDateTime.now().withHour(hours).withMinute(minutes);
  }

  public static String getFullName(PersonalCard clientCard) {
    return String.format("%s %s %s", clientCard.getSecondName(), clientCard.getSurname(),
      clientCard.getName());
  }
}
