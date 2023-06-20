package com.danit.erp.exception.find.name;

import com.danit.erp.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CouldNotFindEmailException extends AbstractException {
  private  static final String MESSAGE = "Не вірний email";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindEmailException() {
    super(CouldNotFindEmailException.STATUS, CouldNotFindEmailException.MESSAGE);
  }

  public CouldNotFindEmailException(Boolean show) {
    super(CouldNotFindEmailException.STATUS, CouldNotFindEmailException.MESSAGE, show);
  }

}