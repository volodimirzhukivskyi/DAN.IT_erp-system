package com.danit.erp.exception;

import org.springframework.http.HttpStatus;

public class CouldNotFindPaymentException extends AbstractException {
  private  static final String MESSAGE = "Вибачте, такого методу оплати немає.";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindPaymentException() {
    super(CouldNotFindPaymentException.STATUS, CouldNotFindPaymentException.MESSAGE);
  }

  public CouldNotFindPaymentException(Boolean show) {
    super(CouldNotFindPaymentException.STATUS, CouldNotFindPaymentException.MESSAGE, show);
  }
}