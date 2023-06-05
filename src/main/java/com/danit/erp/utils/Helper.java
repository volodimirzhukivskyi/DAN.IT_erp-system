package com.danit.erp.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jdk.jshell.execution.LoaderDelegate;

public class Helper {
  public static String convertDate(LocalDateTime localDateTime){

    DateTimeFormatter contractFormatter =
      DateTimeFormatter.ofPattern("dd.MM.yyyy");
   return localDateTime.format(contractFormatter);
  }
}
